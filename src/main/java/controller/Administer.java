package controller;

import Models.Cards.CardClasses.Cards;
import Models.Player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Administer {

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
