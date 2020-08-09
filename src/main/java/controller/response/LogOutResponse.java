package controller.response;

public class LogOutResponse extends Response {


    boolean successful;
    boolean exit;

    public LogOutResponse(boolean successful,boolean exit) {
        setResponseType("LogOutResponse");
        this.successful = successful;
        this.exit=exit;
    }


    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
