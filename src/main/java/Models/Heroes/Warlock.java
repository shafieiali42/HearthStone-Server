package Models.Heroes;

import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.HeroPower.WarlockHeroPower;
import Models.Player.InGamePlayer;
import Visitors.PowerVisitor.SpVisitor.SpVisitor;

import java.util.ArrayList;

public class Warlock extends Heroes {


    private static final ArrayList<Cards> specialCardsOfWarlock = new ArrayList<Cards>();


    public Warlock() {
        this.setName("Warlock");
        this.setHealthPower(35);
        this.setDescription("You will never see anyone beyond him. " +
                "He passes on his life and property and sacrifices something to win the war.");
        this.setIsLock(false);
        this.setHeroPower(new WarlockHeroPower());
        initSpecialCardsOfWarlock();
    }

    public static void initSpecialCardsOfWarlock() {
        for (Cards card : Cards.getAllCards()) {
            boolean isDuplicated = false;
            for (Cards cardInSpecialCardsOfWarlock : specialCardsOfWarlock) {
                if (card.getName().equals(cardInSpecialCardsOfWarlock.getName())) {
                    isDuplicated = true;
                }
            }
            if (!isDuplicated) {
                if (card.getClassOfCard().toLowerCase().trim().equals("warlock")) {
                    specialCardsOfWarlock.add(card);
                }
            }

        }
    }

    public static ArrayList<Cards> getSpecialCardsOfWarlock() {
        return specialCardsOfWarlock;
    }


    @Override
    public void accept(SpVisitor spVisitor, InGamePlayer player, ArrayList<Minion> friendlyBattleGround, ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards, ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards, ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Minion summoned) {
        spVisitor.visit(this, player, friendlyBattleGround, enemyBattleGround, friendlyHandCards,
                enemyHandsCards, friendlyDeckCards, enemyDeckCards, target, targetHero, summoned);
    }
}

