package controller.response;

import java.util.ArrayList;

public class LogInResponse extends Response {


    private boolean successful;
    private String answer;
    private ArrayList<String> allCardsNames;

    public LogInResponse(boolean successful, String answer, ArrayList<String> allCardsNames) {
        setResponseType("LogInResponse");
        this.successful = successful;
        this.answer = answer;
        this.allCardsNames = allCardsNames;
    }


    //getter and setters
    //********************

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<String> getAllCardsNames() {
        return allCardsNames;
    }

    public void setAllCardsNames(ArrayList<String> allCardsNames) {
        this.allCardsNames = allCardsNames;
    }
}
