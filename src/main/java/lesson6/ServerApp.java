package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;

public class ServerApp {
    public static void main(String[] args) {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Сервер запущен, ожидает подключения");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Thread sendMsgThread = new Thread(() -> {
                try {
                    while (true) {
                        Scanner sc = new Scanner(System.in);
                        String str = sc.nextLine();
                        System.out.println("Server: " + str);
                        out.writeUTF(str);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
            }
        });
        sendMsgThread.start();

        while (true) {
            String str = in.readUTF();
            if (str.equalsIgnoreCase("/end")) {
                out.writeUTF(str);
                break;
            } else if (str.toLowerCase(Locale.ROOT).startsWith("/echo")) {
                out.writeUTF("Эхо: " + str);
                continue;
            }
            System.out.println("Client: " + str);
        }
    } catch(
    IOException e)

    {
        e.printStackTrace();
    }
}

}


