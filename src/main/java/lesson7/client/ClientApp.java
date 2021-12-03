package lesson7.client;

import lesson7.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientApp extends JFrame {

    private JTextField msgInputField;
    private JTextArea chatArea;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String login;

    public ClientApp() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareGUI();
    }

    private void openConnection() throws IOException {
        socket = new Socket(Constants.SERVER_ADDRESS, Constants.SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = in.readUTF();
                    if (strFromServer.equalsIgnoreCase(Constants.END_COMMAND)) {
                        break;
                    } else if (strFromServer.startsWith(Constants.AUTH_OK)) {
                        String[] tokens = strFromServer.split("\\s+");
                        this.login = tokens[3];
                        chatArea.append("Успешно авторизован как " + login + "\n");
                    }
                    chatArea.append(strFromServer + "\n");
                }
                chatArea.append("Disconnected");
                chatArea.setEnabled(false);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void closeConnection() {
        try {
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void sendMessage() {
        if (msgInputField.getText().trim().isEmpty()) {
            return;
        }
        try {
            out.writeUTF(msgInputField.getText());
            msgInputField.setText("");
            msgInputField.grabFocus();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
        }

    }

    public void prepareGUI() {
        setBounds(600, 300, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton sendMsgButton = new JButton("Send");
        bottomPanel.add(sendMsgButton, BorderLayout.EAST);
        msgInputField = new JTextField();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputField, BorderLayout.CENTER);
        sendMsgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatArea.append("You: " + msgInputField.getText() + "\n");
                sendMessage();
            }
        });
        msgInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.writeUTF("/end");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientApp::new);

    }
}
