package server;

import Logic.PlayLogic.Game;
import Models.Player.InGamePlayer;
import Models.Player.Player;
import com.google.gson.Gson;
import controller.ClientHandler;
import controller.response.PLayGameResponse;
import controller.response.Response;
import database.DataBase;
import database.DataBaseHandler;
import database.MyPostrgeSqlDataBase;
import jdk.net.Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server extends Thread {

    private int serverPort;
    private ServerSocket serverSocket;
    private volatile boolean running;
    private static HashMap<Player, String> playQueue;
    private static ArrayList<Game> runningGames;
    private static HashMap<String, Socket> sockets;
    private static DataBaseHandler dataBaseHandler=new DataBaseHandler(new MyPostrgeSqlDataBase());


    public Server() {
        try {
            this.serverPort = 8000;
            serverSocket = new ServerSocket(serverPort);
            sockets = new HashMap<>();
            playQueue=new HashMap<>();
            runningGames=new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(this, socket);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static Game giveGameWithPlayer(String userName) {
        for (Game game : runningGames) {
            if (game.getWhitePlayer().getPlayer().getUserName().equalsIgnoreCase(userName)) {
                return game;
            } else if (game.getBlackPlayer().getPlayer().getUserName().equalsIgnoreCase(userName)) {
                return game;
            }
        }
        return null;
    }


    public static InGamePlayer giveAnotherPlayer(String userName) {
        for (Game game : runningGames) {
            if (game.getWhitePlayer().getPlayer().getUserName().equalsIgnoreCase(userName)) {
                return game.getBlackPlayer();
            } else if (game.getBlackPlayer().getPlayer().getUserName().equalsIgnoreCase(userName)) {
                return game.getWhitePlayer();
            }
        }
        return null;
    }


    public static Socket giveSocketWithUserName(String userName) {
        for (String userNameInSockets : Server.sockets.keySet()) {
            if (userNameInSockets.equalsIgnoreCase(userName)) {
                return Server.sockets.get(userNameInSockets);
            }
        }
        return null;
    }

    public static InGamePlayer giveInGamePlayer(String userName) {
        for (Game game : runningGames) {
            if (game.getWhitePlayer().getPlayer().getUserName().equalsIgnoreCase(userName)) {
                return game.getWhitePlayer();
            } else if (game.getBlackPlayer().getPlayer().getUserName().equalsIgnoreCase(userName)) {
                return game.getBlackPlayer();
            }
        }
        return null;
    }


    public static void sendResponse(String userName, Response response) {
        try {
            String message = new Gson().toJson(response);
            PrintWriter printer;
            printer = new PrintWriter(Server.giveSocketWithUserName(userName).getOutputStream());
            printer.println(response.getResponseReceiversToken());
            printer.println(response.getResponseType());
            printer.println(message);
            printer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static HashMap<Player, String> getPlayQueue() {
        return playQueue;
    }

    public static void setPlayQueue(HashMap<Player, String> playQueue) {
        Server.playQueue = playQueue;
    }

    public static ArrayList<Game> getRunningGames() {
        return runningGames;
    }

    public static void setRunningGames(ArrayList<Game> runningGames) {
        Server.runningGames = runningGames;
    }

    public static HashMap<String, Socket> getSockets() {
        return sockets;
    }

    public static void setSockets(HashMap<String, Socket> sockets) {
        Server.sockets = sockets;
    }

    public static DataBaseHandler getDataBaseHandler() {
        return dataBaseHandler;
    }

    public static void setDataBaseHandler(DataBaseHandler dataBaseHandler) {
        Server.dataBaseHandler = dataBaseHandler;
    }
}
