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
    frame.setVisible(true);
    }
}
