package Models.Cards.CardClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public abstract class Weapon extends Cards {

    public static final int NUMBER_OF_Weapons =3;
    @Column
    private int attackPower;
    @Column
    private int durability;
    private boolean hasAttackInThisTurn = false;
    static ArrayList<Weapon> weapons=new ArrayList<Weapon>();

    public Weapon()  {
        super();
        setType("Weapon");
    }




    public int getAttackPower() {
        return attackPower;
    }
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
    public int getDurability() {
        return durability;
    }
    public void setDurability(int durability) {
        this.durability = durability;
    }
    public static ArrayList<Weapon> getWeapons() {
        return weapons;
    }
    public static void setWeapons(ArrayList<Weapon> weapons) {
        Weapon.weapons = weapons;
    }
    public boolean isHasAttackInThisTurn() {
        return hasAttackInThisTurn;
    }
    public void setHasAttackInThisTurn(boolean hasAttackInThisTurn) {
        this.hasAttackInThisTurn = hasAttackInThisTurn;
    }

    @Override
    public String toString(){
        return "Name: "+getName()+"Durability: "+durability+" attack: "+attackPower;
    }

}
