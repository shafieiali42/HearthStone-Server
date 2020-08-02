package controller.response;


import java.util.ArrayList;
import java.util.HashMap;

public class SetAllLittleCardsPanelsResponse extends Response {


    private HashMap<String, Integer> allCardsNameMana;

    public SetAllLittleCardsPanelsResponse(HashMap<String, Integer> allCardsNameMana) {
        this.allCardsNameMana = allCardsNameMana;
    }


    public HashMap<String, Integer> getAllCardsNameMana() {
        return allCardsNameMana;
    }

    public void setAllCardsNameMana(HashMap<String, Integer> allCardsNameMana) {
        this.allCardsNameMana = allCardsNameMana;
    }
}
