package lesson4;

import javax.swing.*;
import java.awt.event.*;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {
    public MyWindow() {
        setTitle("My Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(600, 300, 900, 600);
        Border border = BorderFactory.createLineBorder(Color.BLACK);


        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        add(textArea, BorderLayout.CENTER);

        JTextArea textField = new JTextArea();
        textField.setLineWrap(true);
        textField.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        textField.setEditable(true);

        add(textField, BorderLayout.SOUTH);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = textArea.getText() + "\n";
                textArea.setText(currentText + textField.getText());
                textField.setText("");
            }
        });

        add(sendButton, BorderLayout.EAST);

        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    sendButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        setVisible(true);
    }
}
