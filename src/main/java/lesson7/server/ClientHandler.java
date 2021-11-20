package lesson7.server;

import lesson7.constants.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Optional;

public class ClientHandler {
    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;

    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            new Thread(() -> {
                try {
                    authentication();
                    readMessage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException ex) {
            throw new RuntimeException("ClientHandler creation error.");
        }
    }

    private void authentication() throws IOException {
        Timestamp authStartTimestamp = new Timestamp(System.currentTimeMillis());
        while (true) {
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            if(currentTimestamp.getTime() - authStartTimestamp.getTime() > 120000) {
                sendMessage("Authentication time is out. Disconnecting...");
                closeConnection();
            }
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");
                Optional<String> nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);
                if (nick.isPresent()) {
                    name = nick.get();
                    if (!server.isNickBusy(name)) {
                        sendMessage(Constants.AUTH_OK + name);
                        server.broadcastMessage(name + " connected");
                        server.subscribe(this);
                        return;
                    } else sendMessage("Name is already using");
                } else sendMessage("Wrong login/password");
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();
            if (message.equals(Constants.END_COMMAND)) {
                break;
            }
            if (message.startsWith(Constants.PRIVATE_MESSAGE_FLAG)) {
                String[] tokens = message.split("\\s+");
                String addressee = tokens[1];
                message = name + " to " + addressee + ": " +
                        message.replace(Constants.PRIVATE_MESSAGE_FLAG + " ", "").
                                replace(addressee + " ", "");
                server.sendPrivateMessage(this, addressee, message);
                continue;
            }
            server.broadcastMessage(name + ": " + message);
        }
    }

    public String getName() {
        return this.name;
    }

    private void closeConnection() {
        try {
            server.unsubscribe(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
