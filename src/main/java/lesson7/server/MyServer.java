package lesson7.server;

import lesson7.constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private List<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)) {
            authService = new BaseAuthService();
            authService.start();

            clients = new ArrayList<>();

            while (true) {
                System.out.println("Server awaits connections...");
                Socket socket = server.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Server error");
            e.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMessage(String message) {
        clients.forEach(client -> client.sendMessage(message));
        }

    public synchronized void sendPrivateMessage(ClientHandler sender, String addressee, String message) {
        for (ClientHandler client: clients) {
            if(client.getName().equals(addressee)){
                client.sendMessage(message);
                sender.sendMessage(message);
            } else {
                sender.sendMessage("This person is not in chat: " + addressee);
            }
        }
    }

    public synchronized void broadcastClientList() {
        StringBuilder stringBuilder = new StringBuilder("/clients");
        clients.forEach(client -> stringBuilder.append(client.getName()).append(" "));
        broadcastMessage(stringBuilder.toString());
    }


    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastMessage(client.getName() + " disconnected");
        broadcastClientList();
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientList();
    }

}
