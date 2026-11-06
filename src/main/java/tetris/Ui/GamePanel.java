package main.java.tetris.Ui;

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

    public GamePanel(){
        setBackground(Color.black);
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
        g.setColor(new Color(60,60,60));
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
    if (!gameOver) {
        pieceRenderer.drawPiece(g,currentPiece);
    }
    int sideX = Board_Width * Cell_Size + 30;
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD , 22));
    g.drawString("SCORE", sideX, 50);
    g.drawString(String.valueOf(score), sideX, 85);
    g.setFont(new Font("Arial",Font.PLAIN,14));
    g.drawString("A / D or Arrows: Move", sideX, 140);
    g.drawString("S or down: Soft Drop", sideX, 160);
    g.drawString("R: restart",sideX,180);

    if (gameOver) {
        int boardW = Board_Width * Cell_Size;
        int boardH = Board_Height *Cell_Size;
        g.setColor(new Color(0,0,0,170));
        g.fillRect(0, 0, boardW, boardH);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD , 36));
        drawCentered(g, "GAME OVER", boardW, boardH / 2 - 10);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN,18));
        drawCentered(g, "Final Score: " + score, boardW, boardH /2 + 25);
        drawCentered(g, "Press R to restart", boardW, boardH /2 + 55);
    }

    }
}
