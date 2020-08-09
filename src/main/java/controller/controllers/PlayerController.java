package controller.controllers;

import Models.Cards.CardClasses.Cards;
import Models.Heroes.*;
import Models.Player.ParsePlayerObjectIntoJson;
import Models.Player.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.Server;

import java.io.*;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PlayerController {


    public static Player signIn(String userName, String passWord) {

        boolean validUserNameAndPassword = false;
        for (Player player : getAllPlayer()) {
            if (userName.equals(player.getUserName()) && passWord.equals(player.getPassWord())) {
                validUserNameAndPassword = true;
//                ControllerOfMainComponents.currentPlayer = player;
                switch (player.getCurrentHero().getName()) {
                    case "Mage":
                        Mage mage = new Mage();
                        player.setMage(mage);
                        player.getAvailableHeroes().add(mage);
                        player.setCurrentHero(mage);
                        break;
                    case "Rogue":
                        Rogue rogue = new Rogue();
                        player.setRogue(rogue);
                        player.getAvailableHeroes().add(rogue);
                        player.setCurrentHero(rogue);
                        break;
                    case "Warlock":
                        Warlock warlock = new Warlock();
                        player.setWarlock(warlock);
                        player.getAvailableHeroes().add(warlock);
                        player.setCurrentHero(warlock);
                        break;
                    case "Hunter":
                        Hunter hunter = new Hunter();
                        player.setHunter(hunter);
                        player.getAvailableHeroes().add(hunter);
                        player.setCurrentHero(hunter);
                        break;
                    case "Priest":
                        Priest priest = new Priest();
                        player.setPriest(priest);
                        player.getAvailableHeroes().add(priest);
                        player.setCurrentHero(priest);
                        break;
                }

                ArrayList<Cards> cards = new ArrayList<>();
                for (Cards cards1 : player.getCurrentDeck().getListOfCards()) {
                    for (Cards cards2 : Cards.getAllCards()) {
                        if (cards1.getName().equalsIgnoreCase(cards2.getName())) {
                            cards.add(cards2);
                        }
                    }
                }

                player.getCurrentDeck().setListOfCards(cards);


                player.setOnline(true);
                player.setSignInOrSignup("Signin");
                player.setSignInOrSignup("Signin");
//                player.setLoggerOfMyPlayer();
//                player.getLoggerOfMyPlayer().info("sign_in " + player.getUserName());

                return player;
            }
        }
        return null;
    }


    public static List<Player> getAllPlayer() {
        //            Type type = new TypeToken<List<Player>>() {
//            }.getType();
//            List<Player> playerList = null;
//            playerList = new Gson().fromJson(new FileReader("MinionSpellsWeapons/AllPlayers.json"), type);
//            return playerList;
        List<Player> players = Server.getDataBaseHandler().fetchAll(Player.class);
        return players;
    }

    public static Player signUp(String userName, String passWord) {


        boolean canSignUp = true;
        for (Player player : getAllPlayer()) {
            if (userName.equals(player.getUserName())) {
                canSignUp = false;
            }
        }
        if (canSignUp) {
            Player player = new Player(userName, passWord);
//            ControllerOfMainComponents.currentPlayer = player;
            player.setSignInOrSignup("Signup");
            player.setSignInOrSignup("Signup");
            player.setOnline(true);
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Calendar cal = Calendar.getInstance();
//            player.getLoggerOfMyPlayer().info("USER: " + player.getUserName());
//            player.getLoggerOfMyPlayer().info("CREATED_AT:" + dateFormat.format(cal.getTime()));
//            player.getLoggerOfMyPlayer().info("PASSWORD: " + player.getPassWord());
//            player.getLoggerOfMyPlayer().info("sign_up " + player.getUserName());
            return player;
        } else {
            return null;
        }
    }

    public static boolean logOut(Player player) {
        player.setOnline(false);
        Server.getDataBaseHandler().save(player);
//            ParsePlayerObjectIntoJson.serializePlayer(player);
//        player.getLoggerOfMyPlayer().info("Log_out " + player.getUserName());
//        player.getLoggerOfMyPlayer().getHandlers()[0].close();
//        player = null;
        return true;
    }

    public static boolean deletePlayer(String userName, String password, Player player) {
        if (password.equals(player.getPassWord())) {
            try {
                player.setOnline(false);
                File temp = new File("logs/" + "temp.txt");
                FileReader fileReader = null;
                fileReader = new FileReader("logs/" + player.getUserName() + ".log");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                FileWriter fileWriter = null;
                fileWriter = new FileWriter(temp);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String st = new String();
                while ((st = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(st + "\n");
                    if (st.contains("PASSWORD")) {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Calendar cal = Calendar.getInstance();
                        bufferedWriter.write("DELETED_AT: " + dateFormat.format(cal.getTime()) + "\n");
                    }
                }
                bufferedReader.close();
                bufferedWriter.close();
                fileReader.close();
                fileWriter.close();
                FileReader fileReader1 = new FileReader(temp);
                FileWriter fileWriter1 = new FileWriter("logs/" + player.getUserName() + ".log");
                BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
                BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
                String string = new String();
                while ((string = bufferedReader1.readLine()) != null) {
                    bufferedWriter1.write(string + "\n");
                }
                bufferedWriter1.write("Deleted Account!");
                bufferedReader1.close();
                bufferedWriter1.flush();
                bufferedWriter1.close();
                fileReader1.close();
                fileWriter1.close();
                ParsePlayerObjectIntoJson.removePlayer(player);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return false;
        }
        return false;
    }

}
