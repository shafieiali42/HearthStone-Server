package Models.HeroPower;

import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Heroes.Heroes;
import Models.Player.InGamePlayer;
import Visitors.PowerVisitor.HeroPowerVisitor.VisitorOfPowers;


import java.util.ArrayList;

public class RogueHeroPower extends HeroPower {


    public RogueHeroPower(){
        this.setName("RogueHeroPower");
        this.setMana(3);
        setNeedsTarget(false);
    }


    @Override
    public void accept(VisitorOfPowers visitorOfPowers, InGamePlayer player, ArrayList<Minion> friendlyBattleGround,
                       ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards,
                       ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards,
                       ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Minion summoned, Game game) {

        visitorOfPowers.visit(this,player,friendlyBattleGround,enemyBattleGround,friendlyHandCards,enemyHandsCards,
                friendlyDeckCards,enemyDeckCards,target,targetHero, game);
    }
}
