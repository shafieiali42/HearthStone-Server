package controller.controllers;

import Models.Cards.CardClasses.Cards;
import Models.Deck.Deck;
import Models.Heroes.*;
import Models.Player.Player;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class CollectionController {


    public static void makeNewDeck(Player player,String name, String heroName) {

        player.setDeckToChange(new Deck());
        player.getDeckToChange().setName(name);
//        CollectionState.getInstance().getDeckToChange().setLittleCardsListFromHashMap();

        switch (heroName) {
            case ("Mage"):
                Mage mage = new Mage();
                player.setMage(mage);
                player.setCurrentHero(mage);
                player.getDeckToChange().setHero(mage);
                break;
            case ("Rogue"):
                Rogue rogue = new Rogue();
                player.setRogue(rogue);
                player.setCurrentHero(rogue);
                player.getDeckToChange().setHero(rogue);
                break;
            case ("Warlock"):
                Warlock warlock = new Warlock();
                player.setWarlock(warlock);
                player.setCurrentHero(warlock);
                player.getDeckToChange().setHero(warlock);
                break;
            case ("Hunter"):
                Hunter hunter = new Hunter();
                player.setHunter(hunter);
                player.setCurrentHero(hunter);
                player.getDeckToChange().setHero(hunter);
                break;
            case ("Priest"):
                Priest priest = new Priest();
                player.setPriest(priest);
                player.setCurrentHero(priest);
                player.getDeckToChange().setHero(priest);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + heroName);
        }

    }


    public static ArrayList<Cards> getCardsWithSpecificGroup(String group) {
        ArrayList<Cards> filteredCardsByClassOfCard = new ArrayList<Cards>();
        for (Cards card : Cards.getAllCards()) {
            if (card.getClassOfCard().equalsIgnoreCase(group)) {
                filteredCardsByClassOfCard.add(card);
            }
        }
        return filteredCardsByClassOfCard;
    }


}
