package controller.response;

import utility.constant.Constant;

import javax.swing.*;

public class MakeChangeToDeckToChangeResponse extends Response {


    private String typeOfRequest;
    private String newField;


    public MakeChangeToDeckToChangeResponse(String typeOfRequest, String newField) {
        this.typeOfRequest = typeOfRequest;
        this.newField=newField;
    }


    public String getTypeOfRequest() {
        return typeOfRequest;
    }

    public void setTypeOfRequest(String typeOfRequest) {
        this.typeOfRequest = typeOfRequest;
    }


    public String getNewField() {
        return newField;
    }

    public void setNewField(String newField) {
        this.newField = newField;
    }
}
