package main.java.tetris.Ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

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
    public GamePanel(){
        setBackground(Color.black);
        pieceRenderer = new PieceRenderer();
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

    pieceRenderer.drawPiece(g,currentPiece);
    }
}
