package Models.Heroes;

import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.HeroPower.HeroPower;
import Models.Player.InGamePlayer;
import Visitors.PowerVisitor.SpVisitor.SpVisitable;
import Visitors.PowerVisitor.SpVisitor.SpVisitor;


import java.util.ArrayList;

public class Heroes implements SpVisitable {

    public Heroes() {

    }


    private String name;
    private int healthPower;
    private int attackPower;
    private int firstHealthPower;
    private String description;
    private ArrayList<Cards> DeckOfHero = new ArrayList<Cards>();
    private boolean isLock;
    private int shield;
    private boolean canBeAttacked = true;
    private HeroPower heroPower;


    //getter and setters
    //********************

    public int getFirstHealthPower() {
        return firstHealthPower;
    }

    public void setFirstHealthPower(int firstHealthPower) {
        this.firstHealthPower = firstHealthPower;
    }

    public HeroPower getHeroPower() {
        return heroPower;
    }

    public void setHeroPower(HeroPower heroPower) {
        this.heroPower = heroPower;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public boolean isCanBeAttacked() {
        return canBeAttacked;
    }

    public boolean getCanBeAttacked() {
        return canBeAttacked;
    }

    public void setCanBeAttacked(boolean canBeAttacked) {
        this.canBeAttacked = canBeAttacked;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(boolean lock) {
        isLock = lock;
    }

    public ArrayList<Cards> getDeckOfHero() {
        return DeckOfHero;
    }

    public void setDeckOfHero(ArrayList<Cards> deckOfHero) {
        DeckOfHero = deckOfHero;
    }

    public String getName() {
        return name;
    }

    public int getHealthPower() {
        return healthPower;
    }

    public void setHealthPower(int healthPower) {
        this.healthPower = healthPower;
    }


    @Override
    public String toString() {
        return "[ Name: " + this.getName() + ", healthPower: " + this.healthPower + "]";
    }


    @Override
    public void accept(SpVisitor spVisitor, InGamePlayer player, ArrayList<Minion> friendlyBattleGround,
                       ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards,
                       ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards,
                       ArrayList<Cards> enemyDeckCards, Minion target,
                       Heroes targetHero, Minion summoned) {
    }


}
