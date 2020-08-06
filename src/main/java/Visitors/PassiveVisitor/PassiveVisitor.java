package Visitors.PassiveVisitor;

import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Cards.GameCards.Passives.*;
import Models.Player.InGamePlayer;

import java.util.ArrayList;

public interface PassiveVisitor {


    void visit(FreePower freePower, InGamePlayer player, ArrayList<Minion> friendlyBattleGround,
               ArrayList<Cards> friendlyHandCards, ArrayList<Cards> friendlyDeckCards, Game game);

    void visit(OffCards offCards, InGamePlayer player, ArrayList<Minion> friendlyBattleGround,
               ArrayList<Cards> friendlyHandCards, ArrayList<Cards> friendlyDeckCards, Game game);

    void visit(TwiceDraw twiceDraw, InGamePlayer player, ArrayList<Minion> friendlyBattleGround,
               ArrayList<Cards> friendlyHandCards, ArrayList<Cards> friendlyDeckCards, Game game);

    void visit(ManaJump manaJump, InGamePlayer player, ArrayList<Minion> friendlyBattleGround,
               ArrayList<Cards> friendlyHandCards, ArrayList<Cards> friendlyDeckCards, Game game);

    void visit(Nurse nurse, InGamePlayer player, ArrayList<Minion> friendlyBattleGround,
               ArrayList<Cards> friendlyHandCards, ArrayList<Cards> friendlyDeckCards, Game game);

}
