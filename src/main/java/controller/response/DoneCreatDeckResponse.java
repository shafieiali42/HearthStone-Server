package controller.response;

public class DoneCreatDeckResponse extends Response {


    private String state;


    public DoneCreatDeckResponse(String state) {
        setResponseType("DoneCreatDeckResponse");
        this.state = state;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
