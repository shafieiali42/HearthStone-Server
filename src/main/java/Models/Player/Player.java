package Models.Player;

import Models.Cards.CardClasses.Cards;
import Models.Deck.Deck;
import Models.Heroes.*;
import com.google.gson.annotations.Expose;
import controller.Status;
import utility.Log.LoggerOfProject;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

@Entity
public class Player {


   @Id
    private String userName;
   @Column
    private String passWord;
    @Column
    private String SignInOrSignup;
    @Column
    private int money;
    @Column
    private  Mage mage;
    @Column
    private  Rogue rogue;
    @Column
    private   Warlock warlock;
    @Column
    private  Hunter hunter;
    @Column
    private  Priest priest;
    @Column
    private Heroes currentHero;
    @Column
    private ArrayList<Heroes> availableHeroes = new ArrayList<Heroes>();
    @Column
    private ArrayList<Cards> allCardsOfPlayer = new ArrayList<Cards>();
    @Column
    private ArrayList<Cards> SalableCards = new ArrayList<Cards>();
    @Column
    private ArrayList<Cards> BuyableCards = new ArrayList<Cards>();
    @Column
    private ArrayList<Cards> lockCards = new ArrayList<Cards>();
    @Column
    private ArrayList<Deck> allDecksOfPlayer = new ArrayList<Deck>();
    @Column
    private Deck currentDeck;
    @Enumerated(EnumType.STRING)
    @Column
    private Status playerStatusInGame;
    @Column
    private Deck deckToChange;
    @Column
    private boolean online;
    @Transient
    private transient Logger loggerOfMyPlayer;









    public Player(String userName, String passWord)  {
        this.userName = userName;
        this.passWord = passWord;
        this.mage=new Mage();
        this.rogue=new Rogue();
        this.warlock=new Warlock();
        this.hunter=new Hunter();
        this.priest=new Priest();
        this.currentHero=this.mage;
        this.availableHeroes.add(mage);
        this.availableHeroes.add(rogue);
        this.availableHeroes.add(warlock);
        this.availableHeroes.add(hunter);
        this.availableHeroes.add(priest);
        this.defineFirstCardsForPlayer();
        setLoggerOfMyPlayer();
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


    public ArrayList<Cards> getSalableCards() {
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

    public ArrayList<Cards> getBuyableCards() {
        return BuyableCards;
    }

    public ArrayList<Deck> getAllDecksOfPlayer() {
        return allDecksOfPlayer;
    }

    public ArrayList<Cards> getLockCards() {
        return lockCards;
    }

    public ArrayList<Cards> getAllCardsOfPlayer() {
        return allCardsOfPlayer;
    }

    public Logger getLoggerOfMyPlayer() {
        return loggerOfMyPlayer;
    }

    public Heroes getCurrentHero() {
        return currentHero;
    }

    public ArrayList<Heroes> getAvailableHeroes() {
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

    public void setLoggerOfMyPlayer()  {
        this.loggerOfMyPlayer = LoggerOfProject.getMyLogger("logs/" + this.getUserName() + ".log");
    }

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
}
