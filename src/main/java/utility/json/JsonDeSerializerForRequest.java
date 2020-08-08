package utility.json;

import com.google.gson.Gson;
import controller.request.*;

import java.util.HashMap;

public class JsonDeSerializerForRequest {


    public static HashMap<String, Class> map = new HashMap<>();

    public static void setMap() {
        map.clear();
        map.put("ShowPlayPanelRequest", ShowPlayPanelRequest.class);
        map.put("ChangeBackOfCardRequest", ChangeBackOfCardRequest.class);
        map.put("ChangeVolumeRequest", ChangeVolumeRequest.class);
        map.put("DeletePlayerRequest", DeletePlayerRequest.class);
        map.put("DoneCreatDeckRequest", DoneCreatDeckRequest.class);
        map.put("EndTurnRequest", EndTurnRequest.class);
        map.put("GoBackFromCollectionPages", GoBackFromCollectionPages.class);
        map.put("GoToPageRequest", GoToPageRequest.class);
        map.put("LengthOfMessage", LeftClickRequest.class);
        map.put("LogInRequest", LogInRequest.class);
        map.put("LogOutRequest", LogOutRequest.class);
        map.put("MakeChangeToDeckToChangeRequest", MakeChangeToDeckToChangeRequest.class);
        map.put("MakeNewDeckRequest", MakeNewDeckRequest.class);
        map.put("MouseClickRequest", MouseClickRequest.class);
        map.put("MousePressRequest", MousePressRequest.class);
        map.put("MouseReleasedRequest", MouseReleasedRequest.class);
        map.put("OkButtonDiscoverPageRequest", OkButtonDiscoverPageRequest.class);
        map.put("OkButtonOnFirstThreeCardsPageRequest", OkButtonOnFirstThreeCardsPageRequest.class);
        map.put("PlayGameRequest", PlayGameRequest.class);
        map.put("QuitGameRequest", QuitGameRequest.class);
        map.put("RemoveCardFromDeckToChangeRequest", RemoveCardFromDeckToChangeRequest.class);
        map.put("SetAllLittleCardsPanelsRequest", SetAllLittleCardsPanelsRequest.class);
        map.put("SetPlayerInfoPassiveRequest", SetPlayerInfoPassiveRequest.class);
        map.put("ShowCardsFilteredByManaRequest", ShowCardsFilteredByManaRequest.class);
        map.put("ShowDeckButtonsRequest", ShowDeckButtonsRequest.class);
        map.put("ShowDeckNumberRequest", ShowDeckNumberRequest.class);
        map.put("ShowDeckRequest", ShowDeckRequest.class);
        map.put("ShowGameModesRequest", ShowGameModesRequest.class);
        map.put("ShowSearchCardsRequest", ShowSearchCardsRequest.class);
        map.put("ShowSpecialCardsOfHeroRequest", ShowSpecialCardsOfHeroRequest.class);
        map.put("ShowSpecificCardsRequest", ShowSpecificCardsRequest.class);
        map.put("ShowWalletRequest", ShowWalletRequest.class);
        map.put("TransactionRequest", TransactionRequest.class);
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



