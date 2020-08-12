package Models.Cards.CardClasses;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;

@Entity
public abstract class Minion extends Cards {

    public static final int NUMBER_OF_MINIONS = 16;
    private static ArrayList<Minion> minions = new ArrayList<Minion>();

    public static void setMinions(ArrayList<Minion> minions) {
        Minion.minions = minions;
    }

    @Column
    private int attackPower;
    @Column
    private int healthPower;

    @Transient
    private int firstAttackPower;
    @Transient
    private int firstHealthPower;
    @Transient
    private boolean active = true;
    @Transient
    private boolean canBeAttacked = true; // if we have taunt and it is not taunt then this field would be false:))
    @Transient
    private boolean taunt = false;
    @Transient
    private boolean hasAttackInThisTurn = false;
    @Transient
    private boolean divineShield=false;
    @Transient
    private boolean rush=false;



    public Minion(){
        super();
        setType("Minion");
    }



    public boolean isTaunt() {
        return taunt;
    }

    public boolean isRush() {
        return rush;
    }

    public void setRush(boolean rush) {
        this.rush = rush;
    }

    public static void initFirstAttackAndHp(){
        for (Minion minion:getMinions()){
            minion.firstAttackPower=minion.attackPower;
            minion.firstHealthPower =minion.healthPower;
        }
    }

    public int getFirstAttackPower() {
        return firstAttackPower;
    }

    public void setFirstAttackPower(int firstAttackPower) {
        this.firstAttackPower = firstAttackPower;
    }

    public int getFirstHealthPower() {
        return firstHealthPower;
    }

    public void setFirstHealthPower(int firstHealthPower) {
        this.firstHealthPower = firstHealthPower;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isCanBeAttacked() {
        return canBeAttacked;
    }



    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public boolean isHasAttackInThisTurn() {
        return hasAttackInThisTurn;
    }

    public boolean isDivineShield() {
        return divineShield;
    }

    public void setDivineShield(boolean divineShield) {
        this.divineShield = divineShield;
    }

    public abstract Minion copy();


    //Getter and Setter
    //*****************
    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getHealthPower() {
        return healthPower;
    }

    public void setHealthPower(int healthPower) {
        this.healthPower = healthPower;
    }

    public static ArrayList<Minion> getMinions() {
        return minions;
    }

    public boolean getIsActive() {
        return active;
    }

    public void setIsActive(boolean active) {
        this.active = active;
    }

    public boolean getCanBeAttacked() {
        return canBeAttacked;
    }

    public void setCanBeAttacked(boolean canBeAttacked) {
        this.canBeAttacked = canBeAttacked;
    }

    public boolean getIsTaunt() {
        return taunt;
    }

    public void setIsTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public boolean getHasAttackInThisTurn() {
        return hasAttackInThisTurn;
    }

    public void setHasAttackInThisTurn(boolean hasAttackInThisTurn) {
        this.hasAttackInThisTurn = hasAttackInThisTurn;
    }

    @Override
    public String toString() {
        return "[" + "Name: " + this.getName() + " ,  classOfCard: " + this.getClassOfCard() + " , Hp" + this.getHealthPower()+
                "Attack: :"+this.attackPower+"]";

    }

    //    @Override
//    public int getHp() {
//        return healthPower;
//    }
//
//    @Override
//    public int getAttack() {
//        return attackPower;
//    }
//
//    @Override
//    public void setHp(int hp) {
//        this.healthPower = hp;
//    }
//
//    @Override
//    public void setAttack(int attack) {
//        this.attackPower = attack;
//    }

}
