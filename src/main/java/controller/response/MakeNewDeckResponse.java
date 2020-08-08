package controller.response;

public class MakeNewDeckResponse extends Response {


    private String deckName;
//    private String heroName;


    public MakeNewDeckResponse(String deckName) {
        setResponseType("MakeNewDeckResponse");
        this.deckName = deckName;
//        this.heroName = heroName;
    }


    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

//    public String getHeroName() {
//        return heroName;
//    }
//
//    public void setHeroName(String heroName) {
//        this.heroName = heroName;
//    }
}
