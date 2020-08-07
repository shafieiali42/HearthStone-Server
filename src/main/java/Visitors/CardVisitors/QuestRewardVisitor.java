package Visitors.CardVisitors;

import Logic.PlayLogic.Alliance;
import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Cards.GameCards.MinionCards.UnoptionalMinions.*;
import Models.Cards.GameCards.SpellCards.Spells.*;
import Models.Cards.GameCards.WeaponCards.Ashbringer;
import Models.Cards.GameCards.WeaponCards.BattleAxe;
import Models.Cards.GameCards.WeaponCards.Gearblade;

import java.util.ArrayList;
import java.util.Random;

public class QuestRewardVisitor implements Visitor {

    @Override
    public void visit(CurioCollector curioCollector, Game game) {

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

    }

    @Override
    public void visit(PharaohsBlessing pharaohsBlessing, ArrayList<Minion> battleGround, Minion target, Alliance alliance, Game game) {

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

    }

    @Override
    public void visit(FriendlySmith friendlySmith, ArrayList<Cards> deckCards, Game game) {

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

      game.getCurrentPlayer().setQuestCard(learnDarconic);

        if (playingCard.getType().equalsIgnoreCase("Spell")) {
            learnDarconic.setManaSpendForQuest(learnDarconic.getManaSpendForQuest() + playingCard.getManaCost());
        }

        if (learnDarconic.getManaSpendForQuest() >= 8) {
            Dragon dragon = null;
            for (Cards card : Cards.getAllCards()) {
                if (card.getName().equalsIgnoreCase("Dragon")) {
                    dragon = (Dragon) card;
                }
            }
            battleGround.add(dragon.copy());
        }


    }

    @Override
    public void visit(StrengthInNumbers strengthInNumbers, ArrayList<Minion> battleGround,
                      ArrayList<Cards> deckCards, Cards playingCard, Game game) {


        if (game.getGameMode().equalsIgnoreCase("OfflineGame")) {
            if (playingCard.getType().equalsIgnoreCase("Minion")) {
                strengthInNumbers.setManaSpendForQuest(strengthInNumbers.getManaSpendForQuest() + playingCard.getManaCost());
            }
            if (strengthInNumbers.getManaSpendForQuest() >= 10) {
                if (game.getCurrentAlliance().equals(Alliance.BLACK)) {
                    if (playingCard.getType().equalsIgnoreCase("Minion")) {
                        strengthInNumbers.setManaSpendForQuest(strengthInNumbers.getManaSpendForQuest() + playingCard.getManaCost());
                    }
                    if (strengthInNumbers.getManaSpendForQuest() >= 10) {
                        Minion minion = (Minion) strengthInNumbers.getReward();
                        battleGround.add(minion.copy());
                        deckCards.remove(minion);
                    }
                } else {
                    if (deckCards.size() > 0) {
                        ArrayList<Minion> minions = new ArrayList<>();
                        for (Cards card : deckCards) {
                            if (card.getType().equalsIgnoreCase("Minion")) {
//                                System.out.println(card);
                                Minion minion = (Minion) card;
                                minions.add(minion);
                            }
                        }
                        Random random = new Random();
                        int randomIndex = random.nextInt(minions.size());
                        Minion minion = minions.get(randomIndex);
//                        System.out.println(minion.getName());
                        battleGround.add(minion.copy());
                        deckCards.remove(minion);
                    }
                }
            }
        } else if (game.getGameMode().equalsIgnoreCase("DeckReader")) {
            if (playingCard.getType().equalsIgnoreCase("Minion")) {
                strengthInNumbers.setManaSpendForQuest(strengthInNumbers.getManaSpendForQuest() + playingCard.getManaCost());
            }
            if (strengthInNumbers.getManaSpendForQuest() >= 10) {
                Minion minion = (Minion) strengthInNumbers.getReward();
                battleGround.add(minion.copy());
                deckCards.remove(minion);
            }
        } else {

            if (playingCard.getType().equalsIgnoreCase("Minion")) {
                strengthInNumbers.setManaSpendForQuest(strengthInNumbers.getManaSpendForQuest() + playingCard.getManaCost());
            }
            if (strengthInNumbers.getManaSpendForQuest() >= 10) {
                if (deckCards.size() > 0) {
                    ArrayList<Minion> minions = new ArrayList<>();
                    for (Cards card : deckCards) {
                        if (card.getType().equalsIgnoreCase("Minion")) {
//                            System.out.println(card);
                            Minion minion = (Minion) card;
                            minions.add(minion);
                        }
                    }
                    Random random = new Random();
                    int randomIndex = random.nextInt(minions.size());
                    Minion minion = minions.get(randomIndex);
//                    System.out.println(minion.getName());
                    battleGround.add(minion.copy());
                    deckCards.remove(minion);
                }
            }
        }
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
