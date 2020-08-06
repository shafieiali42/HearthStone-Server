package Visitors.PowerVisitor.HeroPowerVisitor;

import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.HeroPower.*;
import Models.Heroes.Heroes;
import Models.Player.InGamePlayer;

import java.util.ArrayList;

public interface VisitorOfPowers {

    void visit(MageHeroPower mageHeroPower, InGamePlayer player,
               ArrayList<Minion> friendlyBattleGround,
               ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards,
               ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards,
               ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game);

    void visit(RogueHeroPower rogueHeroPower,
               InGamePlayer player,
               ArrayList<Minion> friendlyBattleGround,
               ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards,
               ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards,
               ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game);


    void visit(WarlockHeroPower warlockHeroPower, InGamePlayer player,
               ArrayList<Minion> friendlyBattleGround,
               ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards,
               ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards,
               ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game);

    void visit(HunterHeroPower hunterHeroPower, InGamePlayer player,
               ArrayList<Minion> friendlyBattleGround,
               ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards,
               ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards,
               ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Minion summoned, Game game);

    void visit(PriestHeroPower priestHeroPower, InGamePlayer player,
               ArrayList<Minion> friendlyBattleGround,
               ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards,
               ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards,
               ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game);


}
