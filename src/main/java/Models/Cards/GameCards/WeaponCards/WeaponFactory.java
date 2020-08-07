package Models.Cards.GameCards.WeaponCards;

import Models.Cards.CardClasses.Weapon;

public class WeaponFactory {


    public  Weapon buildWeapon(WeaponNames weaponName){
        Weapon weapon=null;
        switch (weaponName){
            case BattleAxe:
                weapon=new BattleAxe();
                break;
            case Gearblade:
                weapon=new Gearblade();
                break;
            case Ashbringer:
                weapon=new Ashbringer();
                break;
        }
        return weapon;
    }



}
