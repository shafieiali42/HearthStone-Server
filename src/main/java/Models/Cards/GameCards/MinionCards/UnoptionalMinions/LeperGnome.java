package Models.Cards.GameCards.MinionCards.UnoptionalMinions;


import Logic.PlayLogic.Alliance;
import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Heroes.Heroes;
import Visitors.CardVisitors.Visitor;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class LeperGnome extends Minion {


    public LeperGnome(){
        super();
        setName("LeperGnome");
        setClassOfCard("Neutral");
        setMoneyCost(10);
        setManaCost(1);
        setRarity("common");
        setAttackPower(1);
        setHealthPower(1);
        setActive(true);
        setTaunt(false);
        setCanBeAttacked(true);
        setDivineShield(false);
        setRush(false);
    }



    @Override
    public LeperGnome  copy() {
//        System.out.println("Copy of curioCollector:))");
        LeperGnome copy = new LeperGnome();
        copy.setName(this.getName());
        copy.setManaCost(this.getManaCost());
        copy.setRarity(this.getRarity());
        copy.setDescription(this.getDescription());
        copy.setClassOfCard(this.getClassOfCard());
        copy.setType(this.getType());
        copy.setRarity(this.getRarity());
        copy.setIsPlayed(this.isPlayed());
        copy.setFirstAttackPower(this.getFirstAttackPower());
        copy.setFirstHealthPower(this.getFirstHealthPower());
        copy.setAttackPower(this.getAttackPower());
        copy.setHealthPower(this.getHealthPower());
        copy.setIsActive(this.getIsActive());
        copy.setCanBeAttacked(this.getCanBeAttacked());
        copy.setIsTaunt(this.getIsTaunt());
        copy.setHasAttackInThisTurn(this.getHasAttackInThisTurn());
        return copy;
    }

    @Override
    public void accept(Visitor visitor, ArrayList<Minion> battleGround, ArrayList<Cards> handsCards,
                       ArrayList<Cards> deckCards, Minion target, Heroes targetHero, Minion summonedMinion,
                       Cards playingCard, Alliance alliance, Game game){
        visitor.visit(this, game);
    }




}
