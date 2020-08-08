package Models.Heroes;

import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.HeroPower.MageHeroPower;
import Models.Player.InGamePlayer;
import Visitors.PowerVisitor.SpVisitor.SpVisitor;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;

@Entity
public class Mage extends Heroes {

    @Transient
    private static final ArrayList<Cards> specialCardsOfMage = new ArrayList<Cards>();



    public Mage(){
        this.setName("Mage");
        this.setHealthPower(30);
        this.setDescription("She is a skilled wizard who has special skills in using spells.");
        this.setIsLock(false);
        this.setHeroPower(new MageHeroPower());
        initSpecialCardsOfMage();
    }


    public static void initSpecialCardsOfMage() {
        for (Cards card:Cards.getAllCards()){
            boolean isDuplicated=false;
            for (Cards cardInSpecialCardsOfMage:specialCardsOfMage){
                if (card.getName().equals(cardInSpecialCardsOfMage.getName())) {
                    isDuplicated = true;
                    break;
                }
            }
            if (!isDuplicated){
                if (card.getClassOfCard().equalsIgnoreCase("Mage")){
                    specialCardsOfMage.add(card);
                }
            }

        }
    }



    public static ArrayList<Cards> getSpecialCardsOfMage() {
        return specialCardsOfMage;
    }


    @Override
    public void accept(SpVisitor spVisitor, InGamePlayer player, ArrayList<Minion> friendlyBattleGround, ArrayList<Minion> enemyBattleGround, ArrayList<Cards> friendlyHandCards, ArrayList<Cards> enemyHandsCards, ArrayList<Cards> friendlyDeckCards, ArrayList<Cards> enemyDeckCards, Minion target, Heroes targetHero, Minion summoned, Game game) {
        spVisitor.visit(this,player,friendlyBattleGround,enemyBattleGround,friendlyHandCards,
                enemyHandsCards,friendlyDeckCards,enemyDeckCards,target,targetHero,summoned, game);
    }
}
