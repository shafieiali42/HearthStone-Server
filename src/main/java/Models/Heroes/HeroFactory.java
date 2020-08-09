package Models.Heroes;

public class HeroFactory {

    public Heroes buildHero(HeroNames heroName){
        Heroes hero=null;
        switch (heroName){
            case Mage:
                hero=new Mage();
                break;
            case Rogue:
                hero=new Rogue();
                break;
            case Hunter:
                hero=new Hunter();
                break;
            case Priest:
                hero=new Priest();
                break;
            case Warlock:
                hero=new Warlock();
                break;
        }
        return hero;
    }


}
