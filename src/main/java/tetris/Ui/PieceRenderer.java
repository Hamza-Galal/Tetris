package main.java.tetris.Ui;
import java.awt.Color;
import java.awt.Graphics;
import main.java.tetris.Model.Tetromino;

public class PieceRenderer {
private static final int Cell_Size = 30;
    public void drawPiece(Graphics g , Tetromino currentPiece){
        int row = currentPiece.getRow();
        int col = currentPiece.getCol();
        switch (currentPiece.getType()) {
            case "T":
                setColors(g, "T");
                drawBlock(g,col,row);
                drawBlock(g,col+1,row);
                drawBlock(g,col+2,row);
                drawBlock(g,col+1,row+1);
                break;
            case "I":
                setColors(g, "I");
                drawBlock(g, col, row);
                drawBlock(g, col, row+1);
                drawBlock(g, col, row+2);
                drawBlock(g, col, row+3);
                break;
            case "S":
                setColors(g, "S");
                drawBlock(g, col+1, row);
                drawBlock(g, col+2, row);
                drawBlock(g, col+1, row+1);
                drawBlock(g, col, row+1);
                break;
            case "O":
                setColors(g, "O");
                drawBlock(g, col, row);
                drawBlock(g, col+1, row);
                drawBlock(g, col, row+1);
                drawBlock(g, col+1, row+1);
                break;
            case "Z":
                setColors(g, "Z");
                drawBlock(g, col, row);
                drawBlock(g, col+1, row);
                drawBlock(g, col+1, row+1);
                drawBlock(g, col+2, row+1);
                break;

            case "L":
                setColors(g, "L");
                drawBlock(g, col, row);
                drawBlock(g, col, row+1);
                drawBlock(g, col, row+2);
                drawBlock(g, col+1, row+2);
                break;
            case "J":
                setColors(g, "J");
                drawBlock(g, col+2, row);
                drawBlock(g, col+2, row+1);
                drawBlock(g, col+2, row+2);
                drawBlock(g, col+1, row+2);  
                break; 
            default:
                break;
        }  
    }
        public void drawRotatedPiece(Graphics g, Tetromino piece) {
        setColors(g, piece.getType());
        for (int[] b : piece.getBlocks()) {
            drawBlock(g, piece.getCol() + b[0], piece.getRow() + b[1]);
        }
    }
    private void  setColors(Graphics g ,String type){
        switch (type) {
            case "T":
                g.setColor(Color.MAGENTA);
                break;
            case "I":
                g.setColor(Color.CYAN);
                break;
            case "S":
                g.setColor(Color.RED);
                break;
            case "O":
                g.setColor(Color.YELLOW);
                break;
            case "Z":
                g.setColor(Color.GREEN);
                break;
            case "L":
                g.setColor(Color.ORANGE);
                break;
            case "J":
                g.setColor(Color.PINK);
                break;
            default:
                g.setColor(Color.WHITE);
                break;
        }
    }

    private void drawBlock(Graphics g , int col , int row ){

         int x = col * Cell_Size;
         int y = row * Cell_Size;
         Color pieceColor = g.getColor();
        g.fillRect(x, y, Cell_Size, Cell_Size);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, Cell_Size, Cell_Size);
        g.setColor(pieceColor);
    }
    public void drawLockedBlock(Graphics g , int col , int row, String type){
        setColors(g, type);
        int x = col * Cell_Size;
        int y = row * Cell_Size;
        Color pieceColor = g.getColor();
        g.fillRect(x, y, Cell_Size, Cell_Size);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, Cell_Size, Cell_Size);
        g.setColor(pieceColor);
    }
}

