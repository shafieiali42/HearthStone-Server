package controller.response;

public class LogOutResponse extends Response {


    boolean successful;

    public LogOutResponse(boolean successful) {
        setResponseType("LogOutResponse");
        this.successful = successful;
    }


    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }




}
