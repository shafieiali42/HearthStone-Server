package Models.Cards.GameCards.SpellCards.Spells;

import Models.Cards.CardClasses.Spell;

import javax.persistence.Entity;

@Entity
public class Heal extends Spell {


    @Override
    public Heal copy() {
//        System.out.println("Copy of BookOfSpecters:))");
        Heal copy = new Heal();
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

//
//    @Override
//    public void accept(Visitor visitor, ArrayList<Minion> battleGround, ArrayList<Cards> handsCards,
//                       ArrayList<Cards>deckCards, Minion target, Heroes targetHero, Minion summonedMinion,
//                       Cards playingCard, Alliance alliance){
//        visitor.visit(this,battleGround);
//    }

}