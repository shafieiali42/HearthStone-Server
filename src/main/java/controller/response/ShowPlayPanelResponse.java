package controller.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowPlayPanelResponse extends Response {


    private String userName;
    private ArrayList<String> nameOfWhiteHandsCards;
    private ArrayList<String> nameOfBlackHandsCards;
    private ArrayList<String> nameOfWhiteBattleGroundCards;
    private ArrayList<String> nameOfBlackBattleGroundCards;
    private String whitePlayerHeroName;
    private String blackPlayerHeroName;
    private String whitePlayerWeaponName;
    private String blackPlayerWeaponName;
    private String state;
    private String friendlyHeroHp;
    private String enemyHeroHp;
    private String friendlyHeroAttackPower;
    private String enemyHeroAttackPower;
    private String whiteWeaponDurability;
    private String blackWeaponDurability;
    private String whiteWeaponAttackPower;
    private String blackWeaponAttackPower;
    private String whitePlayerMana;
    private HashMap<String, String> friendlyHpHashMap;
    private HashMap<String, String> friendlyAttackPowerHashMap;
    private HashMap<String, String> enemyHpHashMap;
    private HashMap<String, String> enemyAttackPowerHashMap;


    public ShowPlayPanelResponse(String userName, ArrayList<String> nameOfWhiteHandsCards,
                                 ArrayList<String> nameOfBlackHandsCards,
                                 ArrayList<String> nameOfWhiteBattleGroundCards,
                                 ArrayList<String> nameOfBlackBattleGroundCards,
                                 String whitePlayerHeroName, String blackPlayerHeroName,
                                 String whitePlayerWeaponName, String blackPlayerWeaponName,
                                 String state, String friendlyHeroHp, String enemyHeroHp,
                                 String friendlyHeroAttackPower, String enemyHeroAttackPower,
                                 String whiteWeaponDurability, String blackWeaponDurability,
                                 String whiteWeaponAttackPower, String blackWeaponAttackPower,
                                 String whitePlayerMana,
                                 HashMap<String, String> friendlyHpHashMap,
                                 HashMap<String, String> friendlyAttackPowerHashMap,
                                 HashMap<String, String> enemyHpHashMap,
                                 HashMap<String, String> enemyAttackPowerHashMap) {

        setResponseType("ShowPlayPanelResponse");
        this.userName = userName;
        this.nameOfWhiteHandsCards = nameOfWhiteHandsCards;
        this.nameOfBlackHandsCards = nameOfBlackHandsCards;
        this.nameOfWhiteBattleGroundCards = nameOfWhiteBattleGroundCards;
        this.nameOfBlackBattleGroundCards = nameOfBlackBattleGroundCards;
        this.whitePlayerHeroName = whitePlayerHeroName;
        this.blackPlayerHeroName = blackPlayerHeroName;
        this.whitePlayerWeaponName = whitePlayerWeaponName;
        this.blackPlayerWeaponName = blackPlayerWeaponName;
        this.state = state;
        this.friendlyHeroHp = friendlyHeroHp;
        this.enemyHeroHp = enemyHeroHp;
        this.friendlyHeroAttackPower = friendlyHeroAttackPower;
        this.enemyHeroAttackPower = enemyHeroAttackPower;
        this.whiteWeaponDurability = whiteWeaponDurability;
        this.blackWeaponDurability = blackWeaponDurability;
        this.whiteWeaponAttackPower = whiteWeaponAttackPower;
        this.blackWeaponAttackPower = blackWeaponAttackPower;
        this.whitePlayerMana = whitePlayerMana;
        this.friendlyHpHashMap = friendlyHpHashMap;
        this.friendlyAttackPowerHashMap = friendlyAttackPowerHashMap;
        this.enemyHpHashMap = enemyHpHashMap;
        this.enemyAttackPowerHashMap = enemyAttackPowerHashMap;
    }


    //getter and setters
    //********************

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<String> getNameOfWhiteHandsCards() {
        return nameOfWhiteHandsCards;
    }

    public void setNameOfWhiteHandsCards(ArrayList<String> nameOfWhiteHandsCards) {
        this.nameOfWhiteHandsCards = nameOfWhiteHandsCards;
    }

    public ArrayList<String> getNameOfBlackHandsCards() {
        return nameOfBlackHandsCards;
    }

    public void setNameOfBlackHandsCards(ArrayList<String> nameOfBlackHandsCards) {
        this.nameOfBlackHandsCards = nameOfBlackHandsCards;
    }

    public ArrayList<String> getNameOfWhiteBattleGroundCards() {
        return nameOfWhiteBattleGroundCards;
    }

    public void setNameOfWhiteBattleGroundCards(ArrayList<String> nameOfWhiteBattleGroundCards) {
        this.nameOfWhiteBattleGroundCards = nameOfWhiteBattleGroundCards;
    }

    public ArrayList<String> getNameOfBlackBattleGroundCards() {
        return nameOfBlackBattleGroundCards;
    }

    public void setNameOfBlackBattleGroundCards(ArrayList<String> nameOfBlackBattleGroundCards) {
        this.nameOfBlackBattleGroundCards = nameOfBlackBattleGroundCards;
    }

    public String getWhitePlayerHeroName() {
        return whitePlayerHeroName;
    }

    public void setWhitePlayerHeroName(String whitePlayerHeroName) {
        this.whitePlayerHeroName = whitePlayerHeroName;
    }

    public String getBlackPlayerHeroName() {
        return blackPlayerHeroName;
    }

    public void setBlackPlayerHeroName(String blackPlayerHeroName) {
        this.blackPlayerHeroName = blackPlayerHeroName;
    }

    public String getWhitePlayerWeaponName() {
        return whitePlayerWeaponName;
    }

    public void setWhitePlayerWeaponName(String whitePlayerWeaponName) {
        this.whitePlayerWeaponName = whitePlayerWeaponName;
    }

    public String getBlackPlayerWeaponName() {
        return blackPlayerWeaponName;
    }

    public void setBlackPlayerWeaponName(String blackPlayerWeaponName) {
        this.blackPlayerWeaponName = blackPlayerWeaponName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFriendlyHeroHp() {
        return friendlyHeroHp;
    }

    public void setFriendlyHeroHp(String friendlyHeroHp) {
        this.friendlyHeroHp = friendlyHeroHp;
    }

    public String getEnemyHeroHp() {
        return enemyHeroHp;
    }

    public void setEnemyHeroHp(String enemyHeroHp) {
        this.enemyHeroHp = enemyHeroHp;
    }

    public String getFriendlyHeroAttackPower() {
        return friendlyHeroAttackPower;
    }

    public void setFriendlyHeroAttackPower(String friendlyHeroAttackPower) {
        this.friendlyHeroAttackPower = friendlyHeroAttackPower;
    }

    public String getEnemyHeroAttackPower() {
        return enemyHeroAttackPower;
    }

    public void setEnemyHeroAttackPower(String enemyHeroAttackPower) {
        this.enemyHeroAttackPower = enemyHeroAttackPower;
    }

    public String getWhiteWeaponDurability() {
        return whiteWeaponDurability;
    }

    public void setWhiteWeaponDurability(String whiteWeaponDurability) {
        this.whiteWeaponDurability = whiteWeaponDurability;
    }

    public String getBlackWeaponDurability() {
        return blackWeaponDurability;
    }

    public void setBlackWeaponDurability(String blackWeaponDurability) {
        this.blackWeaponDurability = blackWeaponDurability;
    }

    public String getWhiteWeaponAttackPower() {
        return whiteWeaponAttackPower;
    }

    public void setWhiteWeaponAttackPower(String whiteWeaponAttackPower) {
        this.whiteWeaponAttackPower = whiteWeaponAttackPower;
    }

    public String getBlackWeaponAttackPower() {
        return blackWeaponAttackPower;
    }

    public void setBlackWeaponAttackPower(String blackWeaponAttackPower) {
        this.blackWeaponAttackPower = blackWeaponAttackPower;
    }

    public String getWhitePlayerMana() {
        return whitePlayerMana;
    }

    public void setWhitePlayerMana(String whitePlayerMana) {
        this.whitePlayerMana = whitePlayerMana;
    }

    public HashMap<String, String> getFriendlyHpHashMap() {
        return friendlyHpHashMap;
    }

    public void setFriendlyHpHashMap(HashMap<String, String> friendlyHpHashMap) {
        this.friendlyHpHashMap = friendlyHpHashMap;
    }

    public HashMap<String, String> getFriendlyAttackPowerHashMap() {
        return friendlyAttackPowerHashMap;
    }

    public void setFriendlyAttackPowerHashMap(HashMap<String, String> friendlyAttackPowerHashMap) {
        this.friendlyAttackPowerHashMap = friendlyAttackPowerHashMap;
    }

    public HashMap<String, String> getEnemyHpHashMap() {
        return enemyHpHashMap;
    }

    public void setEnemyHpHashMap(HashMap<String, String> enemyHpHashMap) {
        this.enemyHpHashMap = enemyHpHashMap;
    }

    public HashMap<String, String> getEnemyAttackPowerHashMap() {
        return enemyAttackPowerHashMap;
    }

    public void setEnemyAttackPowerHashMap(HashMap<String, String> enemyAttackPowerHashMap) {
        this.enemyAttackPowerHashMap = enemyAttackPowerHashMap;
    }
}
