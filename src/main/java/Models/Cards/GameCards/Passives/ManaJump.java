package Models.Cards.GameCards.Passives;


import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Cards.CardClasses.Passive;
import Models.Player.InGamePlayer;
import Visitors.PassiveVisitor.PassiveVisitor;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class ManaJump extends Passive {

    public ManaJump() {
        setName("ManaJump");
    }



        @Override
    public void accept(PassiveVisitor passiveVisitor, InGamePlayer player, ArrayList<Minion> friendlyBattleGround,
                       ArrayList<Cards> friendlyHandCards, ArrayList<Cards> friendlyDeckCards, Game game) {

        passiveVisitor.visit(this,player,friendlyBattleGround,friendlyHandCards,friendlyDeckCards, game);
    }
}
