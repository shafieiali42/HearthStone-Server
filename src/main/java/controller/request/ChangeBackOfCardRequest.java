package controller.request;

import controller.response.ChangeBackOfCardsResponse;
import controller.response.Response;

public class ChangeBackOfCardRequest extends Request {


    private String userName;
    private String mode;

    public ChangeBackOfCardRequest(String sendersToken, String userName, String mode) {
        setUserName(userName);
        setRequestType("ChangeVolumeRequest");
        setRequestSendersToken(sendersToken);
        this.userName = userName;
        this.mode = mode;
    }

    @Override
    public Response execute() {
        int modeInt =Integer.parseInt(mode);
        if (modeInt == 3) {
            modeInt = 3;
        } else {
            modeInt = modeInt % 3;
        }
        Response response=new ChangeBackOfCardsResponse(modeInt+"");
        return response;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
