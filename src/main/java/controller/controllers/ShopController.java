package controller.controllers;

import Models.Cards.CardClasses.Cards;
import Models.Deck.Deck;
import Models.Player.Player;

import java.util.HashSet;


public class ShopController {


    public static void setBuyableCardsOfPlayer(Player player) {
        player.getBuyableCards().clear();
        for (Cards card : Cards.getAllCards()) {
            boolean isDuplicated = false;
            for (Cards cardInBuyableCards : player.getBuyableCards()) {
                if (card.getName().equals(cardInBuyableCards.getName())) {
                    isDuplicated = true;
                    break;
                }
            }
            boolean isInAllCardsOfPlayer = false;
            if (!isDuplicated) {
                for (Cards cardInAvailableCardsWithThisSituation : player.getAllCardsOfPlayer()) {
                    if (card.getName().equals(cardInAvailableCardsWithThisSituation.getName())) {
                        isInAllCardsOfPlayer = true;
                        break;
                    }
                }
                if (!isInAllCardsOfPlayer) {
                    player.getBuyableCards().add(card);
                }
            }
        }

    }


    public static void setSellableCardsOfPlayer(Player player) {
        player.getSalableCards().clear();
        HashSet<Cards> mergedSetOfAllDeck = new HashSet<Cards>();
        for (Deck deck : player.getAllDecksOfPlayer()) {
            mergedSetOfAllDeck.addAll(deck.getListOfCards());
        }
        for (Cards card : player.getAllCardsOfPlayer()) {
            boolean isInMyDecks = false;
            for (Cards cardsInMyDecks : mergedSetOfAllDeck) {
                if (card.getName().equals(cardsInMyDecks.getName())) {
                    isInMyDecks = true;
                    break;
                }
            }
            if (!isInMyDecks) {
                player.getSalableCards().add(card);
            }
        }
    }


    public static boolean buyCard(Player player, String cardName) {
        boolean canBuyThisCard = false;
        Cards card = null;
        for (Cards cardInBuyableCards : player.getBuyableCards()) {
            if (cardName.equals(cardInBuyableCards.getName())) {
                card = cardInBuyableCards.copy();
                if (player.getMoney() >= cardInBuyableCards.getMoneyCost())
                    player.setMoney(player.getMoney() - cardInBuyableCards.getMoneyCost());
//                player.getLoggerOfMyPlayer().info("Buy: " + cardName);
                canBuyThisCard = true;
            }
        }
        if (canBuyThisCard) {
            player.getBuyableCards().removeIf(card1 -> cardName.equalsIgnoreCase(card1.getName()));
        }

        if (canBuyThisCard) {
            player.getAllCardsOfPlayer().add(card.copy());
//            player.getLoggerOfMyPlayer().info("Buy " + card.getName());
            setBuyableCardsOfPlayer(player);

        }

        return canBuyThisCard;
    }


    public static boolean sellCard(Player player, String cardName) {
        boolean canSellThisCard = false;
        Cards card = null;
        for (Cards cardInSalableCards : player.getSalableCards()) {
            if (cardName.equals(cardInSalableCards.getName())) {
                card = cardInSalableCards.copy();
                player.setMoney(player.getMoney() + card.getMoneyCost());

//                player.getLoggerOfMyPlayer().info("Sell: " + card.getName());
                canSellThisCard = true;
                player.getAllCardsOfPlayer().removeIf(card1 -> cardName.equalsIgnoreCase(card1.getName()));
            }
        }
//        player.getLoggerOfMyPlayer().info("Sell " + card.getName());
        setSellableCardsOfPlayer(player);
        return canSellThisCard;
    }


    public static void setLockCards(Player player) {
        player.getLockCards().clear();
        for (Cards cards : Cards.getAllCards()) {
            if (!player.getAllCardsOfPlayer().contains(cards)) {
                player.getLockCards().add(cards);
            }
        }
    }


}
