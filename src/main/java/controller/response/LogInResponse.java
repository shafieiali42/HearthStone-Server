package controller.response;

public class LogInResponse extends Response {


    private boolean successful;
    private String answer;

    public LogInResponse(boolean successful, String answer) {
        setResponseType("LogInResponse");
        this.successful = successful;
        this.answer = answer;
    }


    //getter and setters
    //********************

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
