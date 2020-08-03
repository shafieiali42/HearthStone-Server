package controller.response;

import java.util.ArrayList;

public class GoToFirstThreeCardPageResponse extends Response {


    private ArrayList<String> firstThreeCardsName;

    public GoToFirstThreeCardPageResponse(ArrayList<String> firstThreeCardsName) {
        this.firstThreeCardsName = firstThreeCardsName;
    }


    public ArrayList<String> getFirstThreeCardsName() {
        return firstThreeCardsName;
    }

    public void setFirstThreeCardsName(ArrayList<String> firstThreeCardsName) {
        this.firstThreeCardsName = firstThreeCardsName;
    }
}
