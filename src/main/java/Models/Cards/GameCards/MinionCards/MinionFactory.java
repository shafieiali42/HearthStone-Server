package Models.Cards.GameCards.MinionCards;

import Models.Cards.CardClasses.Minion;
import Models.Cards.GameCards.MinionCards.UnoptionalMinions.*;

public class MinionFactory {


    public  Minion buildMinion(MinionNames minionName) {
        Minion minion = null;
        switch (minionName) {
            case Mech:
                minion = new Mech();
                break;
            case Dragon:
                minion = new Dragon();
                break;
            case Locusts:
                minion = new Locusts();
                break;
            case Dreadscale:
                minion = new Dreadscale();
                break;
            case LeperGnome:
                minion = new LeperGnome();
                break;
            case Sathrovarr:
                minion = new Sathrovarr();
                break;
            case TombWarden:
                minion = new TombWarden();
                break;
            case CosmicAnomaly:
                minion = new CosmicAnomaly();
                break;
            case SecurityRover:
                minion = new SecurityRover();
                break;
            case SwampKingDred:
                minion = new SwampKingDred();
                break;
            case CurioCollector:
                minion = new CurioCollector();
                break;
            case HighPriestAmet:
                minion = new HighPriestAmet();
                break;
            case NoviceEngineer:
                minion = new NoviceEngineer();
                break;
            case RiverCrocolisk:
                minion = new RiverCrocolisk();
                break;
            case YoungPriestess:
                minion = new YoungPriestess();
                break;
            case CrazedAlchemist:
                minion = new CrazedAlchemist();
                break;
            case MaidenOfTheLake:
                minion = new MaidenOfTheLake();
                break;
            case StormwindKnight:
                minion = new StormwindKnight();
                break;
            case TournamentMedic:
                minion = new TournamentMedic();
                break;
        }
        return minion;
    }


}
