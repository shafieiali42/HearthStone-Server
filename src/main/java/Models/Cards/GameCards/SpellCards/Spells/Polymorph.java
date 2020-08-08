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
public class Polymorph extends Spell {


    public Polymorph(){
        super();
        setName("Polymorph");
        setClassOfCard("Mage");
        setMoneyCost(10);
        setManaCost(1);
        setRarity("common");
    }


    @Override
    public Polymorph copy() {
//        System.out.println("Copy of Polymorph:))");
        Polymorph copy = new Polymorph();
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
    public void accept(Visitor visitor, ArrayList<Minion> battleGround, ArrayList<Cards> handsCards, ArrayList<Cards> deckCards,
                       Minion target, Heroes targetHero, Minion summonedMinion, Cards playingCard, Alliance alliance, Game game){
        visitor.visit(this,battleGround,target,alliance, game);
    }


}
