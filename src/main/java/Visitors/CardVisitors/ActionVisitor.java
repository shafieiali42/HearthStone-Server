package Visitors.CardVisitors;


import Logic.PlayLogic.Alliance;
import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Cards.GameCards.MinionCards.UnoptionalMinions.*;
import Models.Cards.GameCards.SpellCards.UnoptionalSpells.*;
import Models.Cards.GameCards.WeaponCards.Ashbringer;
import Models.Cards.GameCards.WeaponCards.BattleAxe;
import Models.Cards.GameCards.WeaponCards.Gearblade;
import controller.Status;
import controller.controllers.GamePartController;


import java.util.ArrayList;

public class ActionVisitor implements Visitor {


    //Neutrals
    //**********

    @Override
    public void visit(CurioCollector curioCollector, Game game) {
//        System.out.println("Action of CurioCollector");


    }

    @Override
    public void visit(Sathrovarr sathrovarr, ArrayList<Minion> battleGround, ArrayList<Cards> handsCards, ArrayList<Cards> deckCards, Minion target, Game game) {

    }


    @Override
    public void visit(SecurityRover securityRover, ArrayList<Minion> battleGround, Minion target, Game game) {

    }

    @Override
    public void visit(TombWarden tombWarden, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(BookOfSpecters bookOfSpecters, ArrayList<Minion> battleGround, ArrayList<Cards> handsCards, Game game) {

        for (int i = 0; i < 3; i++) {
            Cards card = GamePartController.drawOneCard(game);
            if (card != null) {
                if (!card.getType().equalsIgnoreCase("Spell")) {
                    handsCards.add(card);
                }
            }
        }


    }

    @Override
    public void visit(PharaohsBlessing pharaohsBlessing, ArrayList<Minion> battleGround, Minion target, Alliance alliance, Game game) {

    }


    @Override
    public void visit(Sprint sprint, Game game) {
        System.out.println("Sprint visit");
        for (int i = 0; i < 4; i++) {
            GamePartController.drawCard(game.getCurrentPlayer());//todo
            GamePartController.refreshPlayPanel();
        }
    }


    @Override
    public void visit(SwarmOfLocusts swarmOfLocusts, ArrayList<Minion> battleGround, Game game) {

        int battleGroundSize = battleGround.size();
        Locusts locusts = null;
        for (Cards card : Cards.getAllCards()) {
            if (card.getName().equalsIgnoreCase("Locusts")) {
                locusts = (Locusts) card;
            }
        }

        for (int i = 0; i < 7 - battleGroundSize; i++) {

            battleGround.add(locusts.copy());
        }


    }

    @Override
    public void visit(Locusts locusts, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(Dragon dragon, Game game) {

    }

    @Override
    public void visit(Polymorph polymorph, ArrayList<Minion> battleGround, Minion target, Alliance alliance, Game game) {

    }

    @Override
    public void visit(Ashbringer ashbringer, Game game) {

    }

    @Override
    public void visit(BattleAxe battleAxe, Game game) {

    }


    @Override
    public void visit(Gearblade gearblade, Game game) {

    }


    //Specials
    //**********


    @Override
    public void visit(FriendlySmith friendlySmith, ArrayList<Cards> deckCards, Game game) {

        game.getCurrentPlayer().getPlayer().setPlayerStatusInGame(Status.DISCOVER_THREE_WEAPONS);
        GamePartController.setThreeWeapon();
        GamePartController.setDiscoverPageContentPane();
    }


    @Override
    public void visit(Dreadscale dreadscale, Game game) {

    }

    @Override
    public void visit(SwampKingDred swampKingDred, Minion playingCard, Game game) {

    }


    @Override
    public void visit(HighPriestAmet highPriestAmet, Minion summonedMinion, Game game) {

    }

    @Override
    public void visit(LearnDarconic learnDarconic, ArrayList<Minion> battleGround, Cards playingCard, Game game) {

    }

    @Override
    public void visit(StrengthInNumbers strengthInNumbers, ArrayList<Minion> battleGround, ArrayList<Cards> deckCards, Cards playingCard, Game game) {

    }

    @Override
    public void visit(Mech mech, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(CosmicAnomaly cosmicAnomaly, Game game) {

    }

    @Override
    public void visit(CrazedAlchemist crazedAlchemist, Game game) {

    }

    @Override
    public void visit(LeperGnome leperGnome, Game game) {

    }

    @Override
    public void visit(MaidenOfTheLake maidenOfTheLake, Game game) {

    }

    @Override
    public void visit(NoviceEngineer noviceEngineer, Game game) {

    }

    @Override
    public void visit(RiverCrocolisk riverCrocolisk, Game game) {

    }

    @Override
    public void visit(StormwindKnight stormwindKnight, Game game) {

    }

    @Override
    public void visit(TournamentMedic tournamentMedic, Game game) {

    }

    @Override
    public void visit(YoungPriestess youngPriestess, Game game) {

    }

    @Override
    public void visit(TreeOfLife treeOfLife, ArrayList<Minion> battleGround, Game game) {
        for (Minion minion : battleGround) {
            minion.setHealthPower(minion.getHealthPower() + treeOfLife.getIncreaseHp());
        }
    }

    @Override
    public void visit(Starfire starfire, ArrayList<Minion> battleGround, Game game) {
        for (Minion minion : battleGround) {
            minion.setHealthPower(minion.getHealthPower() + starfire.getIncreaseHp());
        }
    }

    @Override
    public void visit(SinisterStrike sinisterStrike, ArrayList<Minion> battleGround, Game game) {
        for (Minion minion : battleGround) {
            minion.setHealthPower(minion.getHealthPower() + sinisterStrike.getIncreaseHp());
        }
    }

    @Override
    public void visit(Naturalize naturalize, ArrayList<Minion> battleGround, Game game) {
        for (Minion minion : battleGround) {
            minion.setHealthPower(minion.getHealthPower() + naturalize.getIncreaseHp());
        }
    }

    @Override
    public void visit(Moonfire moonfire, ArrayList<Minion> battleGround, Game game) {
        for (Minion minion : battleGround) {
            minion.setHealthPower(minion.getHealthPower() + moonfire.getIncreaseHp());
        }
    }



    @Override
    public void visit(Caltrops caltrops, ArrayList<Minion> battleGround, Game game) {
        for (Minion minion : battleGround) {
            minion.setHealthPower(minion.getHealthPower() + caltrops.getIncreaseHp());
        }
    }

    @Override
    public void visit(BloodfuryPotion bloodfuryPotion, ArrayList<Minion> battleGround, Game game) {
        for (Minion minion : battleGround) {
            minion.setHealthPower(minion.getHealthPower() + bloodfuryPotion.getIncreaseHp());
        }
    }

    @Override
    public void visit(BlessingOfTheAncients blessingOfTheAncients, ArrayList<Minion> battleGround, Game game) {
        for (Minion minion : battleGround) {
            minion.setHealthPower(minion.getHealthPower() + blessingOfTheAncients.getIncreaseHp());
        }
    }

    @Override
    public void visit(BiologyProject biologyProject, ArrayList<Minion> battleGround, Game game) {

        for (Minion minion : battleGround) {
            minion.setHealthPower(minion.getHealthPower() + biologyProject.getIncreaseHp());
        }

    }


}
