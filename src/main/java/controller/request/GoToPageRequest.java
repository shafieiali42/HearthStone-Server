package controller.request;

import controller.response.GoToPageResponse;
import controller.response.Response;

public class GoToPageRequest extends Request {


    private String pageName;



    public GoToPageRequest(String sendersToken, String pageName) {
        setRequestType("GoToPage");
        setRequestSendersToken(sendersToken);
        this.pageName = pageName;
    }



    @Override
    public Response execute() {
        Response response =new GoToPageResponse(pageName);
        return response;
    }


    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
