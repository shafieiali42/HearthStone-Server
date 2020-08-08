package Models.Cards.GameCards.WeaponCards;

import Models.Cards.CardClasses.Weapon;

import javax.persistence.Entity;

@Entity
public class Ashbringer extends Weapon {


    public Ashbringer(){
        super();
        setName("Ashbringer");
        setClassOfCard("Neutral");
        setMoneyCost(10);
        setManaCost(5);
        setRarity("");
        setAttackPower(5);
        setDurability(3);
    }


    @Override
    public Ashbringer copy() {
//        System.out.println("Copy of Dreadscale:))");
        Ashbringer copy = new Ashbringer();
        copy.setName(this.getName());
        copy.setManaCost(this.getManaCost());
        copy.setRarity(this.getRarity());
        copy.setDescription(this.getDescription());
        copy.setClassOfCard(this.getClassOfCard());
        copy.setType(this.getType());
        copy.setRarity(this.getRarity());
        copy.setIsPlayed(this.isPlayed());
        copy.setAttackPower(this.getAttackPower());
        copy.setDurability(this.getDurability());
        return copy;
    }

}
