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
public class FriendlySmith extends Spell {


    public FriendlySmith(){
        super();
        setName("FriendlySmith");
        setClassOfCard("Rogue");
        setMoneyCost(20);
        setManaCost(1);
        setRarity("rare");
    }


    @Override
    public FriendlySmith copy() {
//        System.out.println("Copy of FriendlySmith:))");
        FriendlySmith copy = new FriendlySmith();
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
        visitor.visit(this,deckCards, game);
    }



}
