package Models.Player;

import Models.Cards.CardClasses.Cards;
import Models.Deck.Deck;
import Models.Heroes.*;
import com.google.gson.annotations.Expose;
import controller.Status;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import utility.Log.LoggerOfProject;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Entity
public class Player implements Comparable<Player> {


    @Id
    private String userName;
    @Column
    private String passWord;
    @Column
    private String SignInOrSignup;
    @Column
    private int money;
    @ManyToOne
    private Mage mage;
    @ManyToOne
    private Rogue rogue;
    @ManyToOne
    private Warlock warlock;
    @ManyToOne
    private Hunter hunter;
    @ManyToOne
    private Priest priest;
    @ManyToOne
    private Heroes currentHero;
    @Column
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "availableHeroes")
    private List<Heroes> availableHeroes = new ArrayList<Heroes>();
    @Column
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//todo
    @JoinTable(name = "allCardsOfPlayer")
    private List<Cards> allCardsOfPlayer = new ArrayList<Cards>();
    @Column
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//todo
    @JoinTable(name = "SalableCards")
    private List<Cards> SalableCards = new ArrayList<Cards>();
    @Column
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//todo
    @JoinTable(name = "BuyableCards")
    private List<Cards> BuyableCards = new ArrayList<Cards>();
    @Column
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "lockCards")
    private List<Cards> lockCards = new ArrayList<>();
    @Column
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "allDecksOfPlayer")
    private List<Deck> allDecksOfPlayer = new ArrayList<>();
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Deck currentDeck;
    @Enumerated(EnumType.STRING)
    @Column
    private Status playerStatusInGame;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Deck deckToChange;
    @Column
    private boolean online;
    @Column
    private int numOfCups;





    public Player(){}


    public Player(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.mage = new Mage();
        this.rogue = new Rogue();
        this.warlock = new Warlock();
        this.hunter = new Hunter();
        this.priest = new Priest();
        this.currentHero = this.mage;
        this.availableHeroes.add(mage);
        this.availableHeroes.add(rogue);
        this.availableHeroes.add(warlock);
        this.availableHeroes.add(hunter);
        this.availableHeroes.add(priest);
        this.defineFirstCardsForPlayer();
//        setLoggerOfMyPlayer();
        this.money = 500;
        currentDeck = new Deck();
    }


    public void defineFirstCardsForPlayer() {
        int i = 0;
        for (Cards card : Cards.getAllCards()) {
            if (i < 9) {
                if (card.getClassOfCard().toLowerCase().trim().equals("neutral")) {
                    allCardsOfPlayer.add(card);
                    i++;
                }
            } else {
                break;
            }
        }

        if (Mage.getSpecialCardsOfMage().size() != 0) {
            allCardsOfPlayer.add(Mage.getSpecialCardsOfMage().get(0));
        }
    }


    //getter and setters
    //**********************


    public List<Cards> getSalableCards() {
        return SalableCards;
    }

    public Deck getCurrentDeck() {
        return currentDeck;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getSignInOrSignup() {
        return SignInOrSignup;
    }

    public int getMoney() {
        return money;
    }

    public List<Cards> getBuyableCards() {
        return BuyableCards;
    }

    public List<Deck> getAllDecksOfPlayer() {
        return allDecksOfPlayer;
    }

    public List<Cards> getLockCards() {
        return lockCards;
    }

    public List<Cards> getAllCardsOfPlayer() {
        return allCardsOfPlayer;
    }

//    public Logger getLoggerOfMyPlayer() {
//        return loggerOfMyPlayer;
//    }

    public Heroes getCurrentHero() {
        return currentHero;
    }

    public List<Heroes> getAvailableHeroes() {
        return availableHeroes;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAvailableHeroes(ArrayList<Heroes> availableHeroes) {
        this.availableHeroes = availableHeroes;
    }

    public void setSignInOrSignup(String signInOrSignup) {
        this.SignInOrSignup = signInOrSignup;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

//    public void setLoggerOfMyPlayer() {
//        this.loggerOfMyPlayer = LoggerOfProject.getMyLogger("logs/" + this.getUserName() + ".log");
//    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setCurrentDeck(Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    public Mage getMage() {
        return mage;
    }

    public void setMage(Mage mage) {
        this.mage = mage;
    }

    public Rogue getRogue() {
        return rogue;
    }

    public void setRogue(Rogue rogue) {
        this.rogue = rogue;
    }

    public Warlock getWarlock() {
        return warlock;
    }

    public void setWarlock(Warlock warlock) {
        this.warlock = warlock;
    }

    public Hunter getHunter() {
        return hunter;
    }

    public void setHunter(Hunter hunter) {
        this.hunter = hunter;
    }

    public Priest getPriest() {
        return priest;
    }

    public void setPriest(Priest priest) {
        this.priest = priest;
    }

    public void setCurrentHero(Heroes currentHero) {
        this.currentHero = currentHero;
    }

    public Status getPlayerStatusInGame() {
        return playerStatusInGame;
    }

    public void setPlayerStatusInGame(Status playerStatusInGame) {
        this.playerStatusInGame = playerStatusInGame;
    }

    public Deck getDeckToChange() {
        return deckToChange;
    }

    public void setDeckToChange(Deck deckToChange) {
        this.deckToChange = deckToChange;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getNumOfCups() {
        return numOfCups;
    }

    public void setNumOfCups(int numOfCups) {
        this.numOfCups = numOfCups;
    }

    @Override
    public int compareTo(Player o) {
        if (this.numOfCups>o.numOfCups){
            return -1;
        }else if(this.numOfCups<o.numOfCups){
            return 1;
        }else {
          return (this.userName.compareTo(o.userName));
        }

    }
}
