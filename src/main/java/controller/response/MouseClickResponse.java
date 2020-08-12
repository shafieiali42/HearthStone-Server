package controller.response;

public class MouseClickResponse extends Response {

    private boolean clicked;


    public MouseClickResponse(boolean clicked) {
        setResponseType("MouseClickResponse");
        this.clicked = clicked;
    }


    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
