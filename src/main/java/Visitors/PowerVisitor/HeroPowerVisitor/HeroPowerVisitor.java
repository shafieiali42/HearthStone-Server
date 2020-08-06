package Visitors.PowerVisitor.HeroPowerVisitor;


import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.HeroPower.*;
import Models.Heroes.Heroes;
import Models.Player.InGamePlayer;
import controller.controllers.GamePartController;
import controller.request.ShowPlayPanelRequest;
import controller.response.Response;
import server.Server;


import java.util.ArrayList;
import java.util.Random;

public class HeroPowerVisitor implements VisitorOfPowers {


    @Override
    public void visit(MageHeroPower mageHeroPower, InGamePlayer player, ArrayList<Minion> friendlyBattleGround, ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards, ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards, ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game) {
        System.out.println("Mage HeroPower Visitor");
    }

    @Override
    public void visit(RogueHeroPower rogueHeroPower,
                      InGamePlayer player,
                      ArrayList<Minion> friendlyBattleGround,
                      ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards,
                      ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards,
                      ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game) {


        System.out.println("Rogue HeroPower Visitor");
        Random random = new Random();

        if (player.getCurrentWeapon() != null) {
            int randomIndexForDeck = random.nextInt(enemyDeckCards.size());
            int randomIndexForHand = random.nextInt(enemyHandsCards.size());
            Cards cardInDeck = enemyDeckCards.get(randomIndexForDeck);
            Cards cardsInHands = enemyHandsCards.get(randomIndexForHand);
            enemyDeckCards.remove(randomIndexForDeck);
            enemyHandsCards.remove(randomIndexForHand);
            friendlyHandCards.add(cardInDeck);
            friendlyHandCards.add(cardsInHands);
            Response response=new ShowPlayPanelRequest(game.getCurrentPlayer().getPlayer().getUserName(),"").execute();
            Server.sendResponse(game.getCurrentPlayer().getPlayer().getUserName(),response);
//            GamePartController.refreshPlayPanel();
        } else {
            if (enemyDeckCards.size() > 0) {
                int randomIndex = random.nextInt(enemyDeckCards.size());
                Cards card = enemyDeckCards.get(randomIndex);
                enemyDeckCards.remove(randomIndex);
                friendlyHandCards.add(card);
                Response response=new ShowPlayPanelRequest(game.getCurrentPlayer().getPlayer().getUserName(),"").execute();
                Server.sendResponse(game.getCurrentPlayer().getPlayer().getUserName(),response);
//                GamePartController.refreshPlayPanel();
            }
        }


    }

    @Override
    public void visit(WarlockHeroPower warlockHeroPower, InGamePlayer player, ArrayList<Minion> friendlyBattleGround, ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards, ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards, ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game) {
        System.out.println("Warlock HeroPower Visitor");
        player.getHero().setHealthPower(player.getHero().getHealthPower() - 2);
        Random random = new Random();
        int randomNum = random.nextInt(2);
        boolean jobsDone = false;
        if (randomNum % 2 == 0) {
            Cards card = GamePartController.drawOneCard(game);
            if (card != null) {
                friendlyHandCards.add(card);
                jobsDone = true;
            }
            if (!jobsDone) {
                if (friendlyBattleGround.size() > 0) {
                    int randomIndex = random.nextInt(friendlyBattleGround.size());
                    friendlyBattleGround.get(randomIndex).setHealthPower(friendlyBattleGround.get(randomIndex).getHealthPower() + 1);
                    friendlyBattleGround.get(randomIndex).setAttackPower(friendlyBattleGround.get(randomIndex).getAttackPower() + 1);
                    jobsDone = true;
                }
            }
        } else {
            if (friendlyBattleGround.size() > 0) {
                int randomIndex = random.nextInt(friendlyBattleGround.size());
                friendlyBattleGround.get(randomIndex).setHealthPower(friendlyBattleGround.get(randomIndex).getHealthPower() + 1);
                friendlyBattleGround.get(randomIndex).setAttackPower(friendlyBattleGround.get(randomIndex).getAttackPower() + 1);
                jobsDone = true;
            }
            if (!jobsDone) {
                Cards card = GamePartController.drawOneCard(game);
                if (card != null) {
                    friendlyHandCards.add(card);
                    jobsDone = true;
                }
            }
        }
    }

    @Override
    public void visit(HunterHeroPower hunterHeroPower, InGamePlayer player,
                      ArrayList<Minion> friendlyBattleGround,
                      ArrayList<Minion> enemyBattleGround,
                      ArrayList<Cards> friendlyHandCards,
                      ArrayList<Cards> enemyHandsCards,
                      ArrayList<Cards> friendlyDeckCards,
                      ArrayList<Cards> enemyDeckCards,
                      Minion target, Heroes targetHero, Minion summoned, Game game) {


        System.out.println("Hunter HeroPower Visitor");
    }

    @Override
    public void visit(PriestHeroPower priestHeroPower, InGamePlayer player, ArrayList<Minion> friendlyBattleGround, ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards, ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards, ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Game game) {
        System.out.println("Priest HeroPower Visitor");
    }


}
