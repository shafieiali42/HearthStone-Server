package controller;

import com.google.gson.Gson;
import controller.request.LogInRequest;
import controller.request.Request;
import controller.response.Response;
import server.GenerateAuthtoken;
import server.Server;
import utility.json.JsonDeSerializerForRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ClientHandler extends Thread {


    private Socket socket;
    private Server server;
    PrintWriter printer;
    private ArrayList<Request> requests;
    private String authToken;


    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        requests = new ArrayList<>();
        this.authToken = null;
    }


    @Override
    public void run() {
        int counter = 0;
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            printer = new PrintWriter(socket.getOutputStream());
            String authToken = "";
            String requestName = "";
            String message = "";
            while (true) {
                while (scanner.hasNextLine()) {
                    String text = scanner.nextLine();
                    switch (counter % 3) {
                        case 0:
                            authToken = text;
                            break;
                        case 1:
                            requestName = text;
                            break;
                        case 2:
                            message = text;
                            Request request = JsonDeSerializerForRequest.deSerializeRequest(authToken, requestName, message);
                            if (authToken.equals("null")) {
                                if (request.getRequestType().equalsIgnoreCase("LogInRequest")) {
                                    request.setRequestSendersToken(GenerateAuthtoken.generateNewToken());
                                    this.authToken = request.getRequestSendersToken();
                                    requests.add(request);
                                    executeRequests();
                                }
                            } else {
                                if (request.getRequestSendersToken().equalsIgnoreCase(this.authToken)) {
                                    requests.add(request);
                                    executeRequests();
                                }
                            }
                            break;
                    }
                    counter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToThisClient(String clientName, String responseName, String message) {
        printer.println(clientName);
        printer.println(responseName);
        printer.println(message);
        printer.flush();
    }


    public void executeRequests() {
        Iterator<Request> itr = requests.iterator();
        while (itr.hasNext()) {
            Request request = itr.next();
            Response response = request.execute();
            response.setResponseReceiversToken(request.getRequestSendersToken());
            String message = new Gson().toJson(response);
            sendToThisClient(response.getResponseReceiversToken(), response.getResponseType(), message);
            checkIfShouldMakeTokenNull(response);
            itr.remove();
        }
    }


    public void checkIfShouldMakeTokenNull(Response response){
        if (response.getResponseType().equalsIgnoreCase("LogOutResponse")){
            this.authToken=null;
        }
    }


}
