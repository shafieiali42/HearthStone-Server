package Models.Cards.GameCards.Passives;

import Models.Cards.CardClasses.Passive;

public class PassiveFactory {


    public Passive buildPassive(PassiveNames passiveName){
        Passive passive=null;
        switch (passiveName){
            case Nurse:
                passive=new Nurse();
                break;
            case ManaJump:
                passive=new ManaJump();
                break;
            case OffCards:
                passive=new OffCards();
                break;
            case FreePower:
                passive=new FreePower();
                break;
            case TwiceDraw:
                passive=new TwiceDraw();
                break;
        }
        return passive;
    }


}
