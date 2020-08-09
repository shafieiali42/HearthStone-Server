package Models.Deck;


import Models.Cards.CardClasses.Cards;
import Models.Heroes.Heroes;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
public class Deck implements Comparable<Deck> {

    @Transient
    private static final int MAX_CAPACITY_OF_DECK = 30;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
    @Column
    private String heroName;
    @ManyToOne
    private Heroes hero;
    @Column
    private int numberOfWins;
    @Column
    private int numberOfUses;
    @Column
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Deck_Cards")
    private List<Cards> listOfCards;
    @ManyToOne
    private Cards mostUsedCard;
    @Column
    private int manaAvg;
    @Column
    private int cups;

    @Column
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ElementCollection
    @JoinTable
    @MapKeyColumn
    private Map<String, Integer> usesHashMap = new HashMap<String, Integer>();

    public void initUsesHashMapFromArrayList() {
        usesHashMap.clear();
        for (Cards card : listOfCards) {
            boolean isInHashMap = false;
            for (String key : usesHashMap.keySet()) {
                if (key.equals(card.getName())) {
                    isInHashMap = true;
                    usesHashMap.put(key, usesHashMap.get(key) + 1);
                    break;
                }
            }
            if (!isInHashMap) {
                usesHashMap.put(card.getName(), 1);
            }

        }
    }


    public Deck() {
        this.listOfCards = new ArrayList<Cards>();
    }

    public Deck(String name, String heroName) {
        this.name = name;
        this.heroName = heroName;
        this.numberOfWins = 0;
        this.numberOfUses = 0;
        this.listOfCards = new ArrayList<Cards>();
    }


    public void defineManaAvg() {
        int sum = 0;
        for (Cards card : listOfCards) {
            sum += card.getManaCost();
        }
        manaAvg = sum / listOfCards.size();
    }

    public void defineMostUsedCard() {
        Collections.sort(listOfCards);
        mostUsedCard = listOfCards.get(0);
    }


    //getter and setters
    //*********************

    public static int getMaxCapacityOfDeck() {
        return MAX_CAPACITY_OF_DECK;
    }

    public int getManaAvg() {
        return manaAvg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public Heroes getHero() {
        return hero;
    }

    public void setHero(Heroes hero) {
        this.hero = hero;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int wins) {
        this.numberOfWins = wins;
    }

    public int getNumberOfUses() {
        return numberOfUses;
    }

    public void setNumberOfUses(int numberOfUses) {
        this.numberOfUses = numberOfUses;
    }

    public List<Cards> getListOfCards() {
        return listOfCards;
    }

    public void setListOfCards(ArrayList<Cards> listOfCards) {
        this.listOfCards = listOfCards;
    }

    public Cards getMostUsedCard() {
        return mostUsedCard;
    }

    public void setMostUsedCard(Cards mostUsedCard) {
        this.mostUsedCard = mostUsedCard;
    }

    public Map<String, Integer> getUsesHashMap() {
        return usesHashMap;
    }

    public void setManaAvg(int manaAvg) {
        this.manaAvg = manaAvg;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }


    @Override
    public int compareTo(Deck deck) {
        double a = (double) this.numberOfWins / this.numberOfUses;
        double b = (double) deck.numberOfWins / deck.numberOfUses;
        if(this.cups<deck.cups){
            return 1;
        }else if(this.cups>deck.cups){
            return -1;
        }else if (a < b) {
            return 1;
        } else if (a > b) {
            return -1;
        } else if (a == b) {
            if (this.numberOfWins < deck.numberOfWins) {
                return 1;
            } else if (this.numberOfWins > deck.numberOfWins) {
                return -1;
            } else if (this.numberOfWins == this.numberOfUses) {
                if (this.numberOfUses < deck.numberOfUses) {
                    return 1;
                } else if (this.numberOfUses > deck.numberOfUses) {
                    return -1;
                } else {
                    if (this.manaAvg < deck.manaAvg) {
                        return 1;
                    } else if (this.manaAvg > deck.manaAvg) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        }
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        Deck deck = (Deck) obj;
        return this.name.equals(deck.name);
    }

    @Override
    public String toString() {
        return "Models.Deck Name: " + this.name + "Models.Deck Hero: " + this.hero + " cards: " + this.listOfCards;
    }


}
