package main.java.tetris.Ui;

import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.FontMetrics;
import main.java.tetris.Engine.GameLoop;
import main.java.tetris.Engine.MusicPlayer;
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
    private boolean paused = false;
    private JButton pauseButton;
    private MusicPlayer music = new MusicPlayer();
    

    public GamePanel(){
        retroFont = loadFont();
        setLayout(null);
        pauseButton = new JButton("PAUSE");
        pauseButton.setBounds(Board_Width * Cell_Size + 30, 400, 200, 40);
        pauseButton.setFont(font(12f));
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(e -> togglePause());
        add(pauseButton);
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
                  music.play("Theme.wav");
        
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
        paused = false;
        pauseButton.setText("PAUSE");
        music.setPaused(false);
        repaint();
    }
    public void toggleMusic() {
        music.toggleMute();
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
        if (!gameOver) {
             pieceRenderer.drawRotatedPiece(g, currentPiece);
            }
        int boardW = Board_Width * Cell_Size;
        int boardH = Board_Height * Cell_Size;

      
        g.setColor(new Color(24, 24, 36));
        g.fillRect(boardW, 0, getWidth() - boardW, getHeight());
        g.setColor(new Color(90, 90, 120));
        g.drawLine(boardW, 0, boardW, boardH);

        int sideX = boardW + 30;

     
        g.setColor(new Color(150, 150, 180));
        g.setFont(font(14f));
        g.drawString("SCORE", sideX, 60);
        g.setColor(Color.WHITE);
        g.setFont(font(28f));
        g.drawString(String.valueOf(score), sideX, 115);

     
        g.setColor(new Color(150, 150, 180));
        g.setFont(font(14f));
        g.drawString("CONTROLS", sideX, 200);
        g.setColor(Color.WHITE);
        g.setFont(font(12f));
        g.drawString("A/D: Move", sideX, 235);
        g.drawString("S: Drop", sideX, 262);
        g.drawString("W: Rotate", sideX, 289);
        g.drawString("P: Pause", sideX, 316);
        g.drawString("M: Mute Song", sideX, 343);
        g.drawString("R: Restart", sideX, 370);
       
        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 190));
            g.fillRect(0, 0, boardW, boardH);
            g.setColor(new Color(255, 70, 70));
            g.setFont(font(28f));
            drawCentered(g, "GAME OVER", boardW, boardH / 2 - 15);
            g.setColor(Color.WHITE);
            g.setFont(font(14f));
            drawCentered(g, "Final Score: " + score, boardW, boardH / 2 + 30);
            g.setColor(new Color(150, 150, 180));
            g.setFont(font(12f));
            drawCentered(g, "Press R to restart", boardW, boardH / 2 + 65);
        }
        if (paused) {
            g.setColor(new Color(0, 0, 0, 190));
            g.fillRect(0, 0, boardW, boardH);
            g.setColor(Color.WHITE);
            g.setFont(font(28f));
            drawCentered(g, "PAUSED", boardW, boardH / 2);
          }
    }
        private Font loadFont() {
    try (java.io.InputStream in = GamePanel.class.getResourceAsStream("/resources/PressStart2P-Regular.ttf")) {
        if (in != null) {
            return Font.createFont(Font.TRUETYPE_FONT, in);
        }
        return Font.createFont(Font.TRUETYPE_FONT, new File("resources/PressStart2P-Regular.ttf"));
    } catch (IOException | FontFormatException e) {
        System.out.println("Font not found, using fallback: " + e.getMessage());
        return new Font(Font.MONOSPACED, Font.BOLD, 12);
    }
}

    private Font font(float size) {
        return retroFont.deriveFont(size);
    }
          public boolean isPaused() {
          return paused;
      }

      public void togglePause() {
          if (gameOver) {
              return;
          }
          paused = !paused;
          if (paused) {
              gameLoop.pause();
          } else {
              gameLoop.resume();
          }
          music.setPaused(paused);
          pauseButton.setText(paused ? "PLAY" : "PAUSE");
          repaint();
      }
}
