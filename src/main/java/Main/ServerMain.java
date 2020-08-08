package Main;

import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Cards.CardClasses.Spell;
import Models.Heroes.Mage;
import Models.Heroes.Rogue;
import Models.Heroes.Warlock;
import database.LoadCardsFromDataBase;

public class ServerMain {
    public static void main(String[] args) {

        LoadCardsFromDataBase.loadMinions();
        LoadCardsFromDataBase.loadSpells();
        LoadCardsFromDataBase.loadWeapons();
        LoadCardsFromDataBase.loadPassives();
        Cards.setAllCards();
        Minion.initFirstAttackAndHp();
        Spell.defineQuestAndRewardCardList();
        Mage.initSpecialCardsOfMage();
        Warlock.initSpecialCardsOfWarlock();
        Rogue.initSpecialCardsOfRogue();


    }
}
