package main.java.tetris.Ui;

import java.awt.Color;

import javax.swing.JPanel;

import main.java.tetris.Model.Board;
import main.java.tetris.Model.PieceGenerator;
import main.java.tetris.Model.Tetromino;

public class GamePanel extends JPanel {
    private static final int Board_Width = 10;
    private static final int Board_Height = 20;
    private static final int Cell_Size = 30;
    private Tetromino currentPiece;
    private Board board;
    private PieceGenerator generator;
    public GamePanel(){
        setBackground(Color.black);
        setFocusable(true);
        generator = new PieceGenerator();
        board = new Board();
        currentPiece = generator.generatePiece();
        board.getGrid(); 
    }
    
     public Tetromino getCurrentPiece(){
        return currentPiece;
    }
    public void generateNewPiece(){
        currentPiece = generator.generatePiece();
    }
}
