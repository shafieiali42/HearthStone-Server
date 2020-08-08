package controller.response;



public class ShowTimerResponse extends Response {


    private boolean finished;
    private int secondPassed;

    public ShowTimerResponse(boolean finished, int secondPassed) {
        setResponseType("ShowTimerResponse");
        this.finished = finished;
        this.secondPassed = secondPassed;
    }


    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getSecondPassed() {
        return secondPassed;
    }

    public void setSecondPassed(int secondPassed) {
        this.secondPassed = secondPassed;
    }
}
