package main.java.tetris.Ui;

import javax.swing.JFrame;

public class GameFrame {
 JFrame frame = new JFrame();
    GamePanel panel = new GamePanel();
    public GameFrame(){
    frame.setSize(600,638);
    frame.setResizable(false);
    frame.setTitle("Tetris");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    java.net.URL iconUrl = GameFrame.class.getResource("/resources/icon.png");
    java.awt.Image icon;
    if (iconUrl != null) {
        icon = new javax.swing.ImageIcon(iconUrl).getImage();
    } else {
        icon = new javax.swing.ImageIcon("Tetris/resources/icon.png").getImage();
    }
    frame.setIconImage(icon);
    frame.setVisible(true);
    panel.requestFocusInWindow();
    }
}
