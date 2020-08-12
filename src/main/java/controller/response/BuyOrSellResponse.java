package controller.response;

import java.util.ArrayList;
import java.util.List;

public class BuyOrSellResponse extends Response {


    private boolean successful;
    private String transactionType;
    private ArrayList<String> allCardsNames;


    public BuyOrSellResponse(boolean successful, String transactionType, ArrayList<String> allCardsNames) {
        setResponseType("BuyOrSellResponse");
        this.successful = successful;
        this.transactionType = transactionType;
        this.allCardsNames=allCardsNames;
    }


    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public ArrayList<String> getAllCardsNames() {
        return allCardsNames;
    }

    public void setAllCardsNames(ArrayList<String> allCardsNames) {
        this.allCardsNames = allCardsNames;
    }
}
