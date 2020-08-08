package Visitors.CardVisitors;

import Logic.PlayLogic.Alliance;
import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Cards.GameCards.MinionCards.Minions.*;
import Models.Cards.GameCards.SpellCards.Spells.*;
import Models.Cards.GameCards.WeaponCards.Ashbringer;
import Models.Cards.GameCards.WeaponCards.BattleAxe;
import Models.Cards.GameCards.WeaponCards.Gearblade;

import java.util.ArrayList;

public interface Visitor {


    //Neutrals
    //**********
    void visit(CurioCollector curioCollector, Game game);
    void visit(Sathrovarr sathrovarr, ArrayList<Minion> battleGround, ArrayList<Cards> handsCards,
               ArrayList<Cards> deckCards, Minion target, Game game);

    void visit(SecurityRover securityRover, ArrayList<Minion> battleGround, Minion target, Game game);

    void visit(TombWarden tombWarden, ArrayList<Minion> battleGround, Game game);
    void visit(BookOfSpecters bookOfSpecters, ArrayList<Minion> battleGround, ArrayList<Cards> handsCards, Game game);
    void visit(PharaohsBlessing pharaohsBlessing, ArrayList<Minion> battleGround, Minion target, Alliance alliance, Game game);
    void visit(Sprint sprint, Game game);
    void visit(Ashbringer ashbringer, Game game);
    void visit(BattleAxe battleAxe, Game game);
    void visit(Gearblade gearblade, Game game);
    void visit(SwarmOfLocusts swarmOfLocusts, ArrayList<Minion> battleGround, Game game);
    void visit(Locusts locusts, ArrayList<Minion> battleGround, Game game);
    void visit(Dragon dragon, Game game);

    //Special
    //*********
    void visit(Polymorph polymorph, ArrayList<Minion> battleGround, Minion target, Alliance alliance, Game game);
    void visit(FriendlySmith friendlySmith, ArrayList<Cards> deckCards, Game game);
    void visit(Dreadscale dreadscale, Game game);
    void visit(SwampKingDred swampKingDred, Minion playingCard, Game game);

    void visit(HighPriestAmet highPriestAmet, Minion summonedMinion, Game game);

    //Quest And Reward

    //******************
    void visit(LearnDarconic learnDarconic, ArrayList<Minion> battleGround, Cards playingCard, Game game);

    void visit(StrengthInNumbers strengthInNumbers, ArrayList<Minion> battleGround,
               ArrayList<Cards> deckCards, Cards playingCard, Game game);

    void visit(Mech mech, ArrayList<Minion> battleGround, Game game);


    void visit(CosmicAnomaly cosmicAnomaly, Game game);

    void visit(CrazedAlchemist crazedAlchemist, Game game);

    void visit(LeperGnome leperGnome, Game game);

    void visit(MaidenOfTheLake maidenOfTheLake, Game game);

    void visit(NoviceEngineer noviceEngineer, Game game);

    void visit(RiverCrocolisk riverCrocolisk, Game game);

    void visit(StormwindKnight stormwindKnight, Game game);

    void visit(TournamentMedic tournamentMedic, Game game);

    void visit(YoungPriestess youngPriestess, Game game);

    void visit(TreeOfLife treeOfLife, ArrayList<Minion> battleGround, Game game);

    void visit(Starfire starfire, ArrayList<Minion> battleGround, Game game);

    void visit(SinisterStrike sinisterStrike, ArrayList<Minion> battleGround, Game game);

    void visit(Naturalize naturalize, ArrayList<Minion> battleGround, Game game);

    void visit(Moonfire moonfire, ArrayList<Minion> battleGround, Game game);

    void visit(Caltrops caltrops, ArrayList<Minion> battleGround, Game game);

    void visit(BloodfuryPotion bloodfuryPotion, ArrayList<Minion> battleGround, Game game);

    void visit(BlessingOfTheAncients blessingOfTheAncients, ArrayList<Minion> battleGround, Game game);

    void visit(BiologyProject biologyProject, ArrayList<Minion> battleGround, Game game);
}

