package Models.Cards.GameCards.WeaponCards;

import Models.Cards.CardClasses.Weapon;

import javax.persistence.Entity;

@Entity
public class BattleAxe extends Weapon {


    public BattleAxe(){
        super();
        setName("BattleAxe");
        setClassOfCard("Neutral");
        setMoneyCost(10);
        setManaCost(1);
        setRarity("common");
        setAttackPower(2);
        setDurability(2);
    }


    @Override
    public BattleAxe copy() {
//        System.out.println("Copy of BattleAxe:))");
        BattleAxe copy = new BattleAxe();
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
