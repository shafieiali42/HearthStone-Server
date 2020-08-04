package controller.response;

import java.util.ArrayList;

public class ChangeFirstThreeCardsResponse extends Response {


    private int changedCardIndex;
    private ArrayList<String> firstThreeCardsName;


    public ChangeFirstThreeCardsResponse(int changedCardIndex, ArrayList<String> firstThreeCardsName) {
        this.changedCardIndex = changedCardIndex;
        this.firstThreeCardsName = firstThreeCardsName;
    }



    public int getChangedCardIndex() {
        return changedCardIndex;
    }

    public void setChangedCardIndex(int changedCardIndex) {
        this.changedCardIndex = changedCardIndex;
    }

    public ArrayList<String> getFirstThreeCardsName() {
        return firstThreeCardsName;
    }

    public void setFirstThreeCardsName(ArrayList<String> firstThreeCardsName) {
        this.firstThreeCardsName = firstThreeCardsName;
    }
}
