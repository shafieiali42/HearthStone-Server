package Models.Cards.GameCards.SpellCards;

import Models.Cards.CardClasses.Spell;
import Models.Cards.GameCards.SpellCards.Spells.*;

public class SpellFactory {


    public  Spell buildSpell(SpellNames spellName){
        Spell spell=null;
        switch (spellName){
            case Heal:
                spell=new Heal();
                break;
            case Sprint:
                spell=new Sprint();
                break;
            case Caltrops:
                spell=new Caltrops();
                break;
            case Moonfire:
                spell=new Moonfire();
                break;
            case Starfire:
                spell=new Starfire();
                break;
            case Polymorph:
                spell=new Polymorph();
                break;
            case Naturalize:
                spell=new Naturalize();
                break;
            case TreeOfLife:
                spell=new TreeOfLife();
                break;
            case FriendlySmith:
                spell=new FriendlySmith();
                break;
            case LearnDarconic:
                spell=new LearnDarconic();
                break;
            case BiologyProject:
                spell=new BiologyProject();
                break;
            case BookOfSpecters:
                spell=new BookOfSpecters();
                break;
            case SinisterStrike:
                spell=new SinisterStrike();
                break;
            case SwarmOfLocusts:
                spell=new SwarmOfLocusts();
                break;
            case BloodfuryPotion:
                spell=new BloodfuryPotion();
                break;
            case PharaohsBlessing:
                spell=new PharaohsBlessing();
                break;
            case StrengthInNumbers:
                spell=new StrengthInNumbers();
                break;
            case BlessingOfTheAncients:
                spell=new BlessingOfTheAncients();
                break;
        }
        return spell;
    }



}
