package Models.Cards.GameCards.SpellCards.Spells;

import Logic.PlayLogic.Alliance;
import Logic.PlayLogic.Game;
import Models.Heroes.Heroes;
import Visitors.CardVisitors.Visitor;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Cards.CardClasses.Spell;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Sprint extends Spell {


    public Sprint(){
        super();
        setName("Sprint");
        setClassOfCard("Neutral");
        setMoneyCost(10);
        setManaCost(4);
        setRarity("common");
    }


    @Override
    public Sprint copy() {
//        System.out.println("Copy of Sprint:))");
        Sprint copy = new Sprint();
        copy.setName(this.getName());
        copy.setManaCost(this.getManaCost());
        copy.setRarity(this.getRarity());
        copy.setDescription(this.getDescription());
        copy.setClassOfCard(this.getClassOfCard());
        copy.setType(this.getType());
        copy.setRarity(this.getRarity());
        copy.setIsPlayed(this.isPlayed());
        copy.setAbilities(this.getAbilities());
        copy.setManaNeededForQuest(this.getManaNeededForQuest());
        return copy;
    }


    @Override
    public void accept(Visitor visitor, ArrayList<Minion> battleGround, ArrayList<Cards> handsCards,
                       ArrayList<Cards> deckCards, Minion target, Heroes targetHero, Minion summonedMinion,
                       Cards playingCard, Alliance alliance, Game game){
        visitor.visit(this, game);
    }


}
