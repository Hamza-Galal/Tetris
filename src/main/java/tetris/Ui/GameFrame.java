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
       java.awt.Image icon = null;
    try {
        java.net.URL url = GameFrame.class.getResource("/resources/Icon.png");
        if (url != null) {
            icon = javax.imageio.ImageIO.read(url);
        } else {
            icon = javax.imageio.ImageIO.read(new java.io.File("resources/Icon.png"));
        }
    } catch (Exception e) {
        System.out.println("Icon error: " + e);
    }
    if (icon != null) {
        frame.setIconImage(icon);
    }
    frame.setIconImage(icon);
    frame.setVisible(true);
    panel.requestFocusInWindow();
    }
}
