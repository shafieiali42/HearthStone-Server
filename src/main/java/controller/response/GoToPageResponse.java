package controller.response;

public class GoToPageResponse extends Response {



    private String pageName;

    public GoToPageResponse(String pageName) {
        setResponseType("GoToPageResponse");
        this.pageName = pageName;
    }


    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
