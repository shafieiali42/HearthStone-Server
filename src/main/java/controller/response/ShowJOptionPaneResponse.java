package controller.response;

public class ShowJOptionPaneResponse extends Response {



    private String message;


    public ShowJOptionPaneResponse(String message) {
        setResponseType("ShowJOptionPaneResponse");
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
