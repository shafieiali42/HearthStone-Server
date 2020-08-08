package controller.request;

import controller.response.ChangeVolumeResponse;
import controller.response.Response;

public class ChangeVolumeRequest extends Request {

    private String status;


    public ChangeVolumeRequest(String sendersToken, String status, String userName) {
        setUserName(userName);
        setRequestType("ChangeVolumeRequest");
        setRequestSendersToken(sendersToken);
        this.status = status;
    }

    @Override
    public Response execute() {
        Response response = new ChangeVolumeResponse(status);
        return response;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
