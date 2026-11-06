package main.java.tetris.Ui;

import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.FontMetrics;
import main.java.tetris.Engine.GameLoop;
import main.java.tetris.Input.KeyboardHandler;
import main.java.tetris.Model.Board;
import main.java.tetris.Model.PieceGenerator;
import main.java.tetris.Model.Tetromino;

public class GamePanel extends JPanel {
    private static final int Board_Width = 10;
    private static final int Board_Height = 20;
    private static final int Cell_Size = 30;
    private PieceRenderer pieceRenderer;
    private Tetromino currentPiece;
    private Board board;
    private PieceGenerator generator;
    private GameLoop gameLoop;
    private int score = 0;
    private boolean gameOver = false;
    private Font retroFont;

    public GamePanel(){
        retroFont = loadFont();
        setBackground(new Color(18, 18, 28));
        pieceRenderer = new PieceRenderer();
        setFocusable(true);
        generator = new PieceGenerator();
        board = new Board();
        currentPiece = generator.generatePiece();
        board.getGrid(); 
        KeyboardHandler keyBoard = new KeyboardHandler(this , board); 
        addKeyListener(keyBoard);
        requestFocusInWindow();
        gameLoop = new GameLoop(this , board);
        
    }
    
     public Tetromino getCurrentPiece(){
        return currentPiece;
    }
    public void generateNewPiece(){
        currentPiece = generator.generatePiece();
    }
    public  int getScore(){
        return score;
    }
    public void addScore(int points){
        score += points;
    }
    public boolean isGameOver(){
        return gameOver;
    }
    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }
    public void restartGame(){
        board.reset();
        score = 0;
        gameOver = false;
        currentPiece = generator.generatePiece();
        gameLoop.restart();
        repaint();
    }
    private void drawCentered(Graphics g, String text, int width, int y) {
        FontMetrics fm = g.getFontMetrics();
        g.drawString(text, (width - fm.stringWidth(text)) / 2, y);
    }
     @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(new Color(36, 36, 52));
        for (int col = 0; col < Board_Width; col++) {
            for(int row = 0; row < Board_Height; row++){
            g.drawRect(col*Cell_Size ,row*Cell_Size, Cell_Size, Cell_Size);
        
        }
    }
        String grid [][] = board.getGrid();
          for (int col = 0; col < Board_Width; col++) {
            for(int row = 0; row < Board_Height; row++){
                if (grid[col][row]!=null) {
                    pieceRenderer.drawLockedBlock(g, col, row, grid[col][row]);
                }
        }
    }

    pieceRenderer.drawPiece(g,currentPiece);
    }
}
