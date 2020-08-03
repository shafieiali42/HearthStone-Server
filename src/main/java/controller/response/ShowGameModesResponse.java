package controller.response;


import javax.swing.*;

public class ShowGameModesResponse extends Response {



    private String userName;
    private Object[] possibilities;

    public ShowGameModesResponse(String userName, Object[] possibilities) {
        this.userName = userName;
        this.possibilities = possibilities;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Object[] getPossibilities() {
        return possibilities;
    }

    public void setPossibilities(Object[] possibilities) {
        this.possibilities = possibilities;
    }






}
