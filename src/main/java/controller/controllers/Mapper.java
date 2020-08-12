package controller.controllers;


import Models.Cards.CardClasses.Cards;
import java.util.ArrayList;


public class Mapper {

    private boolean AddedBeforeForBeingBetween = false;

    public boolean isAddedBeforeForBeingBetween() {
        return AddedBeforeForBeingBetween;
    }

    public void setAddedBeforeForBeingBetween(boolean addedBeforeForBeingBetween) {
        AddedBeforeForBeingBetween = addedBeforeForBeingBetween;
    }


    private ArrayList<Order> requests;
    private Cards cards;

    public Cards getCards() {
        return cards;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }

    public Mapper() {
        requests = new ArrayList<Order>();
    }
}
