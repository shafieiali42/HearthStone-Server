package utility.json;

import com.google.gson.Gson;
import controller.request.Request;

import java.util.HashMap;

public class JsonDeSerializerForRequest {


    public static HashMap<String, Class> map = new HashMap<>();

    public static void setMap() {
        map.clear();

    }


    public static Request deSerializeRequest(String sendesToken, String requestName, String requestString) {
        setMap();
        Gson gson = new Gson();
        Class classOfCard = map.get(requestName);
        Request request = (Request) gson.fromJson(requestString, classOfCard);
        request.setRequestSendersToken(sendesToken);
        return request;
    }



}



