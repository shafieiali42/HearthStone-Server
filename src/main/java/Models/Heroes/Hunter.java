package Models.Heroes;


import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.HeroPower.HunterHeroPower;
import Models.Player.InGamePlayer;
import Visitors.PowerVisitor.SpVisitor.SpVisitor;


import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;

@Entity
public class Hunter extends Heroes {

    @Transient
    private static final ArrayList<Cards> specialCardsOfHunter = new ArrayList<Cards>();



    public Hunter(){
        this.setName("Hunter");
        this.setHealthPower(30);
        this.setDescription("");
        this.setIsLock(true);
        this.setHeroPower(new HunterHeroPower());
        initSpecialCardsOfHunter();
    }


    public static void initSpecialCardsOfHunter() {
        for (Cards card:Cards.getAllCards()){
            boolean isDuplicated=false;
            for (Cards cardInSpecialCardsOfHunter:specialCardsOfHunter){
                if (card.getName().equals(cardInSpecialCardsOfHunter.getName())) {
                    isDuplicated = true;
                    break;
                }
            }
            if (!isDuplicated){
                if (card.getClassOfCard().toLowerCase().trim().equals("hunter")){
                    specialCardsOfHunter.add(card);
                }
            }

        }
    }


    public static ArrayList<Cards> getSpecialCardsOfHunter() {
        return specialCardsOfHunter;
    }

    @Override
    public void accept(SpVisitor spVisitor, InGamePlayer player, ArrayList<Minion> friendlyBattleGround,
                       ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards,
                       ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards,
                       ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Minion summoned, Game game) {

        spVisitor.visit(this,player,friendlyBattleGround,enemyBattleGround,friendlyHandCards,
                enemyHandsCards,friendlyDeckCards,enemyDeckCards,target,targetHero,summoned, game);


    }


}
