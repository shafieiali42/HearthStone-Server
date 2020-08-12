package controller.response;

public class WriteOnLogPanelResponse extends Response {


    private String log;

    public WriteOnLogPanelResponse(String log) {
        setResponseType("WriteOnLogPanelResponse");
        this.log = log;
    }



    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
