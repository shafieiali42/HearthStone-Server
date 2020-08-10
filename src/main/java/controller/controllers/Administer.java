package controller.controllers;

import Models.Cards.CardClasses.Cards;
import Models.Deck.Deck;
import Models.Player.Player;
import server.Server;

import javax.swing.*;
import java.util.*;

public class Administer {


    public static String givetMyRank(String userName){
        List<Player> players = Server.getDataBaseHandler().fetchAll(Player.class);
        Collections.sort(players);
        for (int i=0;i<players.size();i++){
            if (players.get(i).getUserName().equalsIgnoreCase(userName)){
                return ((i+1)+"");
            }
        }
        return null;
    }


    public static ArrayList<String> givePlayersAroundMe(String userName, String cupOrName) {
        ArrayList<String> result = new ArrayList<>();
        List<Player> players = Server.getDataBaseHandler().fetchAll(Player.class);
        Collections.sort(players);
        int index = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUserName().equalsIgnoreCase(userName)) {
                index = i;
                break;
            }
        }

        int lastIndex = Math.min(index + 5, players.size() - 1);
        int firstIndex = Math.max(0, index - 5);
        for (int i = firstIndex; i < lastIndex; i++) {
            if (cupOrName.equalsIgnoreCase("name")) {
                result.add(players.get(i).getUserName());
            } else {
                result.add(players.get(i).getNumOfCups() + "");
            }
        }
        return result;
    }


    public static ArrayList<String> giveBestPlayers(String cupOrName) {
        ArrayList<String> names = new ArrayList<>();
        List<Player> players = Server.getDataBaseHandler().fetchAll(Player.class);
        Collections.sort(players);
        for (int i = 0; i < Math.min(10,players.size()); i++) {
            if (cupOrName.equalsIgnoreCase("name")) {
                names.add(players.get(i).getUserName());
            } else {
                names.add(players.get(i).getNumOfCups() + "");
            }
        }
        return names;
    }





    public static void setBuyableCard(Player player) {
        ShopController.setBuyableCardsOfPlayer(player);
    }

    public static void setSalableCard(Player player) {
        ShopController.setSellableCardsOfPlayer(player);
    }


    public static boolean buyShopStateCard(Player player, String cardName) {
        return ShopController.buyCard(player, cardName);
    }

    public static boolean sellShopStateCard(Player player, String cardName) {
        return ShopController.sellCard(player, cardName);
    }


    public static Cards giveCardWithName(String cardName) {
        for (Cards cards : Cards.getAllCards()) {
            if (cards.getName().equalsIgnoreCase(cardName)) {
                return cards.copy();
            }
        }
        return null;
    }

    public static boolean isShopStateCardInMyDecks(Player player, String cardName) {

        for (Deck deck : player.getAllDecksOfPlayer()) {
            if (deck.getListOfCards().contains(giveCardWithName(cardName))) {
                return true;
            }
        }
        return false;
    }


    public static void addGivenCardToCollectionDeck(Player player, String cardName,
                                                    boolean isLock) { //todo


        Map<String, Integer> usesHashMap = player.getDeckToChange().getUsesHashMap();

        Cards cards = null;
        for (Cards cards1 : Cards.getAllCards()) {
            if (cards1.getName().equals(cardName)) {
                cards = cards1.copy();
                break;
            }
        }

        boolean isInMap=false;
        for (int i = 0; i < usesHashMap.keySet().size(); i++) {
            assert cards != null;
            if (cards.getName().equalsIgnoreCase
                    (usesHashMap.keySet().toArray()[i].toString())) {
                isInMap = true;
                if (Integer.parseInt(usesHashMap.values().toArray()[i].toString()) < 2) {

                    if (!isLock) {
                        player.getDeckToChange().getListOfCards().add(cards);
                    }
//                    DeckViewer.getInstance().showCardsInDecK();
                    int k = Integer.parseInt(usesHashMap.values().toArray()[i].toString()) + 1;
                    usesHashMap.put(usesHashMap.keySet().toArray()[i].toString(), k);
                    break;
                } else if (!isLock) {
                    JOptionPane.showMessageDialog(null,
                            "You have two card of this card in your Deck!",
                            "Add To Deck Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (!isInMap){
            player.getDeckToChange().getListOfCards().add(cards);
            usesHashMap.put(cardName,1);
        }

    }


    public static void sortDecksOfPlayer(Player player) {
        Collections.sort(player.getAllDecksOfPlayer());
    }

    public static ArrayList<String> giveListOfCardsNames(List<Cards> cards) {
        ArrayList<String> names = new ArrayList<>();
        for (Cards card : cards) {
            names.add(card.getName());
        }
        return names;
    }


}
