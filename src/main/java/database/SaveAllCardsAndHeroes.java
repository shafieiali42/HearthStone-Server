package database;

import Models.Cards.GameCards.MinionCards.MinionFactory;
import Models.Cards.GameCards.MinionCards.MinionNames;
import Models.Cards.GameCards.Passives.PassiveFactory;
import Models.Cards.GameCards.Passives.PassiveNames;
import Models.Cards.GameCards.SpellCards.SpellFactory;
import Models.Cards.GameCards.SpellCards.SpellNames;
import Models.Cards.GameCards.WeaponCards.WeaponFactory;
import Models.Cards.GameCards.WeaponCards.WeaponNames;
import Models.Heroes.HeroFactory;
import Models.Heroes.HeroNames;

public class SaveAllCardsAndHeroes {


    private static DataBaseHandler dataBaseHandler = new DataBaseHandler(new MyPostrgeSqlDataBase());
    private static MinionFactory minionFactory = new MinionFactory();
    private static SpellFactory spellFactory = new SpellFactory();
    private static WeaponFactory weaponFactory = new WeaponFactory();
    private static PassiveFactory passiveFactory = new PassiveFactory();
    private static HeroFactory heroFactory=new HeroFactory();

    public static void main(String[] args) {
        saveCards();
        saveHeroes();
    }

    private static void saveHeroes(){
        for (HeroNames heroName:HeroNames.values()){
            dataBaseHandler.save(heroFactory.buildHero(heroName));
        }
    }

    private static void saveMinions(){
        for (MinionNames minionNames : MinionNames.values()) {
            dataBaseHandler.save(minionFactory.buildMinion(minionNames));
        }
    }

    private static void saveSpells(){
        for (SpellNames spellNames : SpellNames.values()) {
            dataBaseHandler.save(spellFactory.buildSpell(spellNames));
        }
    }

    private static void saveWeapons(){
        for (WeaponNames weaponNames : WeaponNames.values()) {
            dataBaseHandler.save(weaponFactory.buildWeapon(weaponNames));
        }
    }

    private static void savePassive(){
        for (PassiveNames passiveNames : PassiveNames.values()) {
            dataBaseHandler.save(passiveFactory.buildPassive(passiveNames));
        }
    }

    public static void saveCards() {
       saveMinions();
       saveSpells();
       saveWeapons();
       savePassive();
    }


}