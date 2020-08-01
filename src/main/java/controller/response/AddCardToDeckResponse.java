package controller.response;

import java.util.ArrayList;
import java.util.HashMap;

public class AddCardToDeckResponse extends Response {

    private String userName;
    private HashMap<String,Integer> usesMap;


    public AddCardToDeckResponse(String userName, HashMap<String, Integer> usesMap) {
        this.userName = userName;
        this.usesMap = usesMap;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public HashMap<String, Integer> getUsesMap() {
        return usesMap;
    }

    public void setUsesMap(HashMap<String, Integer> usesMap) {
        this.usesMap = usesMap;
    }
}
