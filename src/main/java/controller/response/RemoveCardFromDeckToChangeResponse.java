package controller.response;



import java.util.HashMap;
import java.util.Map;

public class RemoveCardFromDeckToChangeResponse extends Response {


    private String userName;
    private Map<String, Integer> usesMap;


    public RemoveCardFromDeckToChangeResponse(String userName, Map<String, Integer> usesMap) {
        setResponseType("RemoveCardFromDeckToChangeResponse");
        this.userName = userName;
        this.usesMap = usesMap;
    }




    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Map<String, Integer> getUsesMap() {
        return usesMap;
    }

    public void setUsesMap(HashMap<String, Integer> usesMap) {
        this.usesMap = usesMap;
    }
}
