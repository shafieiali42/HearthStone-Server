package controller.response;


import java.util.HashMap;
import java.util.Map;

public class ShowDeckResponse extends Response {


    private Map<String ,Integer> usesHashMap;


    public ShowDeckResponse(Map<String, Integer> usesHashMap) {
        setResponseType("ShowDeckResponse");
        this.usesHashMap = usesHashMap;
    }




    public Map<String, Integer> getUsesHashMap() {
        return usesHashMap;
    }

    public void setUsesHashMap(Map<String, Integer> usesHashMap) {
        this.usesHashMap = usesHashMap;
    }
}
