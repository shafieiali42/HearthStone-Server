package controller.request;

import controller.response.Response;

public abstract class Request {

    private String requestSendersToken;
    private String requestType;
    private String userName;

    public abstract Response execute();


    //getter and setters
    //********************

    public String getRequestSendersToken() {
        return requestSendersToken;
    }

    public void setRequestSendersToken(String requestSendersToken) {
        this.requestSendersToken = requestSendersToken;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

