package Logic;


import Models.Player.InGamePlayer;
import Models.Player.Player;
import com.google.gson.Gson;
import controller.controllers.GamePartController;
import controller.response.PLayGameResponse;
import controller.response.Response;
import controller.response.ShowTimerResponse;
import database.DataBase;
import server.Server;

import java.io.IOException;
import java.io.PrintWriter;

public class MyTimer extends Thread {

    private long time1;
    private long time2;
    private int secondPassed = 0;
    private volatile boolean isRunning = true;
    private String name;

    public MyTimer(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        time1 = System.currentTimeMillis();
        time2 = System.currentTimeMillis();

        while (true) {
            while (isRunning) {
                time2 = System.currentTimeMillis();
                while ((time2 - time1) >= 1000) {
                    time1 = System.currentTimeMillis();
                    time2 = System.currentTimeMillis();
                    secondPassed++;
//                    System.out.println(secondPassed);
                    if (secondPassed >= 40 && secondPassed < 60) {
                        try {
                            Response responseForOtherPlayer = new ShowTimerResponse(false, secondPassed);
                            String message = new Gson().toJson(responseForOtherPlayer);
                            PrintWriter printer;
                            printer = new PrintWriter(Server.giveSocketWithUserName(name).getOutputStream());
                            printer.println(responseForOtherPlayer.getResponseReceiversToken());
                            printer.println(responseForOtherPlayer.getResponseType());
                            printer.println(message);
                            printer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        PlayPanel.getInstance().setTime((60-secondPassed)+"");
//                        GamePartController.setNeedTimer(true);
//                        System.out.println("You Have Only 20 seconds:))");
                    }

                    if (secondPassed == 60) {
                        try {
                            Response responseForOtherPlayer = new ShowTimerResponse(true, secondPassed);
                            String message = new Gson().toJson(responseForOtherPlayer);
                            PrintWriter printer;
                            printer = new PrintWriter(Server.giveSocketWithUserName(name).getOutputStream());
                            printer.println(responseForOtherPlayer.getResponseReceiversToken());
                            printer.println(responseForOtherPlayer.getResponseType());
                            printer.println(message);
                            printer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Player player = DataBase.fetchPlayer(name);
                        InGamePlayer whitePlayer = Server.giveInGamePlayer(name);
                        GamePartController.endTurn(whitePlayer);
//                        Mapper.endTurn();
                        isRunning = false;
//                        GamePartController.setNeedTimer(false);
//                        PlayPanel.getInstance().setEndTurn(true);
//                        GamePartController.refreshPlayPanel();
//                        System.out.println("End Turn:))");
                    }
                }
            }
        }
    }

    public int getSecondPassed() {
        return secondPassed;
    }

    public void pause() {
        isRunning = false;
    }

    public void reStart() {
//        System.out.println("reStart");
        isRunning = false;
        time1 = System.currentTimeMillis();
        time2 = System.currentTimeMillis();
        secondPassed = 0;
        isRunning = true;
    }
}
