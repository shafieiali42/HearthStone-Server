package controller.response;

public class ChangeVolumeResponse extends Response {


    private String mode;


    public ChangeVolumeResponse(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
