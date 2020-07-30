package controller.response;

public class DeletePlayerResponse extends Response {


    boolean successful;

    public DeletePlayerResponse(boolean successful) {
        setResponseType("DeletePlayerResponse");
        this.successful = successful;
    }


    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
