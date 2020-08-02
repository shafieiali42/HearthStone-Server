package controller.response;


import java.util.HashMap;

public class ShowDeckResponse extends Response {


    private HashMap<String ,Integer> usesHashMap;


    public ShowDeckResponse(HashMap<String, Integer> usesHashMap) {
        this.usesHashMap = usesHashMap;
    }




    public HashMap<String, Integer> getUsesHashMap() {
        return usesHashMap;
    }

    public void setUsesHashMap(HashMap<String, Integer> usesHashMap) {
        this.usesHashMap = usesHashMap;
    }
}
