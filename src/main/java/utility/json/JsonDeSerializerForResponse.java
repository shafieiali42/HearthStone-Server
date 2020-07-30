package utility.json;


import com.google.gson.Gson;
import controller.response.Response;

import java.util.HashMap;


public class JsonDeSerializerForResponse {


    public static HashMap<String, Class> map = new HashMap<>();

    public static void setMap() {
        map.clear();

    }


    public static Response deSerializeResponse(String receiver, String responseName, String responseString) {
        setMap();
        Gson gson = new Gson();
        Class classOfCard = map.get(responseName);
        Response response = (Response) gson.fromJson(responseString, classOfCard);
        response.setResponseReceiversToken(receiver);
        return response;
    }
}
