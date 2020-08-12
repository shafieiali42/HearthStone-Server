package Models.HeroPower;


import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Heroes.Heroes;
import Models.Player.InGamePlayer;
import Visitors.PowerVisitor.HeroPowerVisitor.HeroPowerVisitable;
import Visitors.PowerVisitor.HeroPowerVisitor.VisitorOfPowers;


import javax.persistence.Entity;
import java.util.ArrayList;

public class HeroPower implements HeroPowerVisitable {



    private String name;

    private int mana;

    private boolean needsTarget;
    private boolean hasAttackInThisTurn = false;
    private int numberOfPermitUse = 1;




    @Override
    public void accept(VisitorOfPowers visitorOfPowers, InGamePlayer player,
                       ArrayList<Minion> friendlyBattleGround, ArrayList<Minion> enemyBattleGround,
                       ArrayList<Cards> friendlyHandCards, ArrayList<Cards> enemyHandsCards,
                       ArrayList<Cards> friendlyDeckCards, ArrayList<Cards> enemyDeckCards,
                       Minion target, Heroes targetHero, Minion summoned, Game game) {}





    //getter and setters
    //********************

    public int getNumberOfPermitUse() {
        return numberOfPermitUse;
    }
    public void setNumberOfPermitUse(int numberOfPermitUse) {
        this.numberOfPermitUse = numberOfPermitUse;
    }
    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getHasAttackInThisTurn() {
        return hasAttackInThisTurn;
    }
    public void setHasAttackInThisTurn(boolean hasAttackInThisTurn) {
        this.hasAttackInThisTurn = hasAttackInThisTurn;
    }
    public boolean isNeedsTarget() {
        return needsTarget;
    }
    public void setNeedsTarget(boolean needsTarget) {
        this.needsTarget = needsTarget;
    }
    public boolean isHasAttackInThisTurn() {
        return hasAttackInThisTurn;
    }
}
