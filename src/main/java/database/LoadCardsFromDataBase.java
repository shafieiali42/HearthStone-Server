package database;

import Models.Cards.CardClasses.*;


import java.util.ArrayList;

public class LoadCardsFromDataBase {

    private static DataBaseHandler dataBaseHandler=new DataBaseHandler(new MyPostrgeSqlDataBase());


    public static boolean isInList(ArrayList<?extends Cards> list,Cards card){
        boolean result=false;
        for (Cards cards : list) {
            if (cards.getName().equalsIgnoreCase(card.getName())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void loadMinions(){
        ArrayList<Minion> minions= (ArrayList<Minion>) dataBaseHandler.fetchAll(Minion.class);
        for (Minion minion:minions){
            if (!isInList(Minion.getMinions(),minion)){
                Minion.getMinions().add(minion);
            }
        }
    }

    public static void loadSpells(){
        ArrayList<Spell> spells= (ArrayList<Spell>) dataBaseHandler.fetchAll(Spell.class);
        for (Spell spell:spells){
            if (!isInList(Spell.getSpells(),spell)){
                Spell.getSpells().add(spell);
            }
        }
    }

    public static void loadWeapons(){
        ArrayList<Weapon> weapons= (ArrayList<Weapon>) dataBaseHandler.fetchAll(Weapon.class);
        for (Weapon weapon:weapons){
            if (!isInList(Weapon.getWeapons(),weapon)){
                Weapon.getWeapons().add(weapon);
            }
        }
    }

    public static void loadPassives(){
        Passive.setPassives((ArrayList<Passive>)dataBaseHandler.fetchAll(Passive.class));
    }
}
