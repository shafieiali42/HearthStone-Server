package utility.json;


import com.google.gson.Gson;
import controller.response.*;

import java.util.HashMap;


public class JsonDeSerializerForResponse {


    public static HashMap<String, Class> map = new HashMap<>();

    public static void setMap() {
        map.clear();
        map.put("AddCardToDeckResponse", AddCardToDeckResponse.class);
        map.put("ChangeBackOfCardsResponse", ChangeBackOfCardsResponse.class);
        map.put("ChangeFirstThreeCardsResponse", ChangeFirstThreeCardsResponse.class);
        map.put("ChangeVolumeResponse", ChangeVolumeResponse.class);
        map.put("DeletePlayerResponse", DeletePlayerResponse.class);
        map.put("DiscoverPageResponse", DiscoverPageResponse.class);
        map.put("DoneCreatDeckResponse", DoneCreatDeckResponse.class);
        map.put("GoToFirstThreeCardPageResponse", GoToFirstThreeCardPageResponse.class);
        map.put("GoToPageResponse", GoToPageResponse.class);
        map.put("LogInResponse", LogInResponse.class);
        map.put("LogOutResponse", LogOutResponse.class);
        map.put("MakeChangeToDeckToChangeResponse", MakeChangeToDeckToChangeResponse.class);
        map.put("MakeNewDeckResponse", MakeNewDeckResponse.class);
        map.put("PlayCardResponse", PlayCardResponse.class);
        map.put("PLayGameResponse", PLayGameResponse.class);
        map.put("QuitGameResponse", QuitGameResponse.class);
        map.put("RemoveCardFromDeckToChangeResponse", RemoveCardFromDeckToChangeResponse.class);
        map.put("ReStartDiscoverPageSetting", ReStartDiscoverPageSetting.class);
        map.put("SetAllLittleCardsPanelsResponse", SetAllLittleCardsPanelsResponse.class);
        map.put("ShowBuyAndSellCardResponse", ShowBuyAndSellCardResponse.class);
        map.put("ShowDeckButtonsResponse", ShowDeckButtonsResponse.class);
        map.put("ShowDeckNumberResponse", ShowDeckNumberResponse.class);
        map.put("ShowDeckResponse", ShowDeckResponse.class);
        map.put("ShowDiscoverPageResponse", ShowDiscoverPageResponse.class);
        map.put("ShowGameModesResponse", ShowGameModesResponse.class);
        map.put("ShowJOptionPaneResponse", ShowJOptionPaneResponse.class);
        map.put("ShowPlayPanelResponse", ShowPlayPanelResponse.class);
        map.put("ShowSpecificCardsResponse", ShowSpecificCardsResponse.class);
        map.put("ShowTimerResponse", ShowTimerResponse.class);
        map.put("ShowWalletResponse", ShowWalletResponse.class);
        map.put("TransactionResponse", TransactionResponse.class);
        map.put("ShowRankResponse", ShowRankResponse.class);
        map.put("ShowChatResponse", ShowChatResponse.class);
        map.put("MouseClickResponse",MouseClickResponse.class);
        map.put("WriteOnLogPanelResponse",WriteOnLogPanelResponse.class);
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
