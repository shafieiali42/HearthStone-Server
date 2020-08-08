package database;

import Models.Cards.CardClasses.Minion;
import Models.Cards.CardClasses.Passive;
import Models.Cards.CardClasses.Spell;
import Models.Cards.CardClasses.Weapon;


import java.util.ArrayList;

public class LoadCardsFromDataBase {

    private static DataBaseHandler dataBaseHandler=new DataBaseHandler(new MyPostrgeSqlDataBase());

    public static void loadMinions(){
       Minion.setMinions((ArrayList<Minion>) dataBaseHandler.fetchAll(Minion.class));
    }

    public static void loadSpells(){
        Spell.setSpells((ArrayList<Spell>)dataBaseHandler.fetchAll(Spell.class));
    }

    public static void loadWeapons(){
        Weapon.setWeapons((ArrayList<Weapon>)dataBaseHandler.fetchAll(Weapon.class));
    }

    public static void loadPassives(){
        Passive.setPassives((ArrayList<Passive>)dataBaseHandler.fetchAll(Passive.class));
    }
}
