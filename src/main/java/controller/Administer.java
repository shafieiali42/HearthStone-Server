package controller;

import Models.Cards.CardClasses.Cards;
import Models.Deck.Deck;
import Models.Player.Player;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Administer {


    public static boolean buyShopStateCard(Player player,String cardName) {
        return ShopController.buyCard(player, cardName);
    }

    public static boolean sellShopStateCard(Player player,String cardName) {
        return ShopController.sellCard(player,cardName);
    }


    public static Cards giveCardWithName(String cardName){
        for (Cards cards:Cards.getAllCards()){
            if (cards.getName().equalsIgnoreCase(cardName)){
                return cards.copy();
            }
        }
        return null;
    }

    public static boolean isShopStateCardInMyDecks(Player player,String cardName) {

        for (Deck deck : player.getAllDecksOfPlayer()) {
            if (deck.getListOfCards().contains(giveCardWithName(cardName))) {
                return true;
            }
        }
        return false;
    }


    public static void addGivenCardToCollectionDeck(Player player, String cardName,
                                                    boolean isLock){ //todo


        HashMap<String,Integer> usesHashMap=player.getDeckToChange().getUsesHashMap();

        Cards cards = null;
        for (Cards cards1 : Cards.getAllCards()) {
            if (cards1.getName().equals(cardName)) {
                cards = cards1.copy();
                break;
            }
        }

        for (int i = 0; i < usesHashMap.keySet().size(); i++) {
            assert cards != null;
            if (cards.getName().equalsIgnoreCase
                    (usesHashMap.keySet().toArray()[i].toString())) {
                if (Integer.parseInt(usesHashMap.values().toArray()[i].toString()) < 2) {

                    if (!isLock) {
                        player.getDeckToChange().getListOfCards().add(cards);
                    }
//                    DeckViewer.getInstance().showCardsInDecK();
                    int k = Integer.parseInt(usesHashMap.values().toArray()[i].toString())+1;
                    usesHashMap.put(usesHashMap.keySet().toArray()[i].toString(),k);
                    break;
                } else if (!isLock) {
                    JOptionPane.showMessageDialog(null,
                            "You have two card of this card in your Deck!",
                            "Add To Deck Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }



    public static void sortDecksOfPlayer(Player player) {
        Collections.sort(player.getAllDecksOfPlayer());
    }

    public static ArrayList<String> giveListOfCardsNames(ArrayList<Cards> cards) {
        ArrayList<String> names=new ArrayList<>();
        for (Cards card:cards){
            names.add(card.getName());
        }
        return names;
    }


}
