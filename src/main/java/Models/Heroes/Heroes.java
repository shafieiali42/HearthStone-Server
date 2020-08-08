package Models.Heroes;

import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.HeroPower.HeroPower;
import Models.Player.InGamePlayer;
import Visitors.PowerVisitor.SpVisitor.SpVisitable;
import Visitors.PowerVisitor.SpVisitor.SpVisitor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;

@Entity
public abstract class Heroes implements SpVisitable {

    public Heroes() {

    }

    @Id
    private String name;
    @Column
    private int healthPower;
    @Column
    private int attackPower;
    @Column
    private int firstHealthPower;
    @Column
    private String description;
    @Column
    private boolean isLock;
    @Transient
    private HeroPower heroPower;
    //    private int shield;
    @Transient
    private boolean canBeAttacked = true;


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

    public boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(boolean lock) {
        isLock = lock;
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
                       ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero,
                       Minion summoned, Game game) {

    }
}
