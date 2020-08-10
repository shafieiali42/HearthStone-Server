package controller.response;

public class ShowChatResponse extends Response{


    private String text;

    public ShowChatResponse(String text) {
        setResponseType("ShowChatResponse");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
