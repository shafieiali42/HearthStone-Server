package Visitors.PowerVisitor.HeroPowerVisitor;


import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.HeroPower.*;
import Models.Heroes.Heroes;
import Models.Player.InGamePlayer;
import controller.Status;

import java.util.ArrayList;

public class TargetVisitorOfPowers implements VisitorOfPowers {


    @Override
    public void visit(MageHeroPower mageHeroPower, InGamePlayer player, ArrayList<Minion> friendlyBattleGround, ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards, ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards, ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game) {
        System.out.println("mage target Visitor");
        game.getCurrentPlayer().getPlayer().setPlayerStatusInGame(controller.Status.CHOOSE_TARGET_FOR_HERO_POWERS);
    }

    @Override
    public void visit(RogueHeroPower rogueHeroPower, InGamePlayer player, ArrayList<Minion> friendlyBattleGround, ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards, ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards, ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game) {

    }

    @Override
    public void visit(WarlockHeroPower warlockHeroPower, InGamePlayer player, ArrayList<Minion> friendlyBattleGround, ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards, ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards, ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game) {

    }

    @Override
    public void visit(HunterHeroPower hunterHeroPower, InGamePlayer player, ArrayList<Minion> friendlyBattleGround, ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards, ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards, ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Minion summoned, Game game) {

    }


    @Override
    public void visit(PriestHeroPower priestHeroPower, InGamePlayer player, ArrayList<Minion> friendlyBattleGround, ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards, ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards, ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game) {
        game.getCurrentPlayer().getPlayer().setPlayerStatusInGame(Status.CHOOSE_TARGET_FOR_HERO_POWERS);
    }
}
