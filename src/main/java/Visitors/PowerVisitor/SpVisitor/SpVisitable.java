package Visitors.PowerVisitor.SpVisitor;

import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Heroes.Heroes;
import Models.Player.InGamePlayer;

import java.util.ArrayList;

public interface SpVisitable {



    void accept(SpVisitor spVisitor, InGamePlayer player, ArrayList<Minion> friendlyBattleGround,
                ArrayList<Minion>enemyBattleGround, ArrayList<Cards>friendlyHandCards,
                ArrayList<Cards>enemyHandsCards, ArrayList<Cards>friendlyDeckCards,
                ArrayList<Cards>enemyDeckCards, Minion target, Heroes targetHero, Minion summoned);

}
