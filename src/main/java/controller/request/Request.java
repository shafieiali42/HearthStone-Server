package controller.request;

import controller.response.Response;

public  class Request {

    private String requestSendersToken;
    private String requestType;
    private String userName;

    public  Response execute(){return null;}


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

