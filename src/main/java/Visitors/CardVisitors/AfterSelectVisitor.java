package Visitors.CardVisitors;


import Logic.PlayLogic.Alliance;
import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Cards.CardClasses.Weapon;
import Models.Cards.GameCards.MinionCards.UnoptionalMinions.*;
import Models.Cards.GameCards.SpellCards.UnoptionalSpells.*;
import Models.Cards.GameCards.WeaponCards.Ashbringer;
import Models.Cards.GameCards.WeaponCards.BattleAxe;
import Models.Cards.GameCards.WeaponCards.Gearblade;
import controller.Status;
import controller.controllers.GamePartController;
import controller.request.ShowPlayPanelRequest;
import controller.response.ReStartDiscoverPageSetting;
import controller.response.Response;
import controller.response.ShowJOptionPaneResponse;
import server.Server;


import javax.swing.*;
import java.util.ArrayList;

public class AfterSelectVisitor implements Visitor {
    @Override
    public void visit(CurioCollector curioCollector, Game game) {

    }

    @Override
    public void visit(Sathrovarr sathrovarr, ArrayList<Minion> battleGround, ArrayList<Cards> handsCards, ArrayList<Cards> deckCards, Minion target, Game game) {

        Minion minion = target.copy();
        minion.setHealthPower(minion.getFirstHealthPower());
        minion.setAttackPower(minion.getFirstAttackPower());
        deckCards.add(minion);

        Minion minion2 = target.copy();
        minion2.setHealthPower(minion2.getFirstHealthPower());
        minion2.setAttackPower(minion2.getFirstAttackPower());
        if (handsCards.size() < 12) {
            handsCards.add(minion2);
        }

        Minion minion3 = target.copy();
        minion3.setHealthPower(minion3.getFirstHealthPower());
        minion3.setAttackPower(minion3.getFirstAttackPower());
        if (battleGround.size() < 7) {
            battleGround.add(minion3);
        }

        Response response = new ShowPlayPanelRequest(game.getCurrentPlayer().getPlayer().getUserName(), "").execute();
        Server.sendResponse(game.getCurrentPlayer().getPlayer().getUserName(), response);
//        GamePartController.refreshPlayPanel();

    }


    @Override
    public void visit(SecurityRover securityRover, ArrayList<Minion> battleGround, Minion target, Game game) {

    }

    @Override
    public void visit(TombWarden tombWarden, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(BookOfSpecters bookOfSpecters, ArrayList<Minion> battleGround, ArrayList<Cards> handsCards, Game game) {

    }

    @Override
    public void visit(PharaohsBlessing pharaohsBlessing, ArrayList<Minion> battleGround, Minion target, Alliance alliance, Game game) {

        if (alliance.equals(Alliance.BLACK)) {

            Server.sendResponse(game.getCurrentPlayer().getPlayer().getUserName(),
                    new ShowJOptionPaneResponse("you cant do it on enemy minions"));

//            JOptionPane.showMessageDialog(GamePartController.getMyMainFrame(),
//                    "you cant do it on enemy minions", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            target.setAttackPower(target.getAttackPower() + 4);
            target.setHealthPower(target.getHealthPower() + 4);
            target.setIsTaunt(true);
            target.setDivineShield(true);
        }

    }


    @Override
    public void visit(Sprint sprint, Game game) {

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

    @Override
    public void visit(SwarmOfLocusts swarmOfLocusts, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(Locusts locusts, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(Dragon dragon, Game game) {

    }

    @Override
    public void visit(Polymorph polymorph, ArrayList<Minion> battleGround, Minion target, Alliance alliance, Game game) {

        if (alliance.equals(Alliance.WHITE)) {
            Response response = new ShowJOptionPaneResponse("you cant do it on your friendly minion");
            Server.sendResponse(game.getCurrentPlayer().getPlayer().getUserName(), response);//todo
//            JOptionPane.showMessageDialog(GamePartController.getMyMainFrame(),
//                    "you cant do it on your friendly minion", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            target.setHealthPower(1);
            target.setAttackPower(1);
        }


    }

    @Override
    public void visit(FriendlySmith friendlySmith, ArrayList<Cards> deckCards, Game game) {

        Weapon weapon = (Weapon) (GamePartController.getSelectedWeapon(game).copy());
        weapon.setAttackPower(weapon.getAttackPower() + 2);
        weapon.setDurability(weapon.getDurability() + 2);
        deckCards.add(3, weapon);
        game.getCurrentPlayer().getPlayer().setPlayerStatusInGame(Status.PLAY_PAGE);
//        GamePartController.setGamePageContentPane();
        Server.sendResponse(game.getCurrentPlayer().getPlayer().getUserName(),
                new ShowPlayPanelRequest(game.getCurrentPlayer().getPlayer().getUserName(), "").execute());//todo
        Server.sendResponse(game.getCurrentPlayer().getPlayer().getUserName(), new ReStartDiscoverPageSetting());
//        MyMainFrame.getInstance().setContentPane(GamePage.getInstance());
//        GamePartController.reStartDiscoverPageSetting();
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

    }

    @Override
    public void visit(Starfire starfire, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(SinisterStrike sinisterStrike, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(Naturalize naturalize, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(Moonfire moonfire, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(Caltrops caltrops, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(BloodfuryPotion bloodfuryPotion, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(BlessingOfTheAncients blessingOfTheAncients, ArrayList<Minion> battleGround, Game game) {

    }

    @Override
    public void visit(BiologyProject biologyProject, ArrayList<Minion> battleGround, Game game) {

    }


}
