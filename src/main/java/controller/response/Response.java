package controller.response;

import java.net.Socket;

public abstract class Response {



    private String responseReceiversToken;
    private String responseType;




    //getter and setters
    //********************

    public String getResponseReceiversToken() {
        return responseReceiversToken;
    }
    public void setResponseReceiversToken(String responseReceiversToken) {
        this.responseReceiversToken = responseReceiversToken;
    }
    public String getResponseType() {
        return responseType;
    }
    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }



}
