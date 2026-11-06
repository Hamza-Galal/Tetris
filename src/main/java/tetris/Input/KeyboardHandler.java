package main.java.tetris.Input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import main.java.tetris.Model.Board;
import main.java.tetris.Model.Tetromino;
import main.java.tetris.Ui.GamePanel;

public class KeyboardHandler implements KeyListener {
    private GamePanel panel;
    private Board board;
  

public KeyboardHandler(GamePanel panel,Board board) {
    this.panel = panel;
    this.board = board;
}
    
 @Override
    public void keyTyped(KeyEvent e) {
       
    }
    @Override
    public void keyPressed(KeyEvent e) {
        Tetromino currentPiece = panel.getCurrentPiece();
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            if (board.canMove(currentPiece, currentPiece.getCol() - 1, currentPiece.getRow())) {
                currentPiece.moveLeft();
            }
            panel.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            if (board.canMove(currentPiece, currentPiece.getCol() , currentPiece.getRow() + 1)) {
                currentPiece.moveDown();
            }
            panel.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            if (board.canMove(currentPiece, currentPiece.getCol() + 1, currentPiece.getRow())) {
                currentPiece.moveRight();
            }
            panel.repaint();   
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            currentPiece.rotateClockwise();
            if (!board.canMove(currentPiece, currentPiece.getCol(), currentPiece.getRow())) {
                int[] kicks = {-1, 1, -2, 2};
                boolean fitted = false;
                for (int k : kicks) {
                    if (board.canMove(currentPiece, currentPiece.getCol() + k, currentPiece.getRow())) {
                        currentPiece.setCol(currentPiece.getCol() + k);
                        fitted = true;
                        break;
                    }
                }
                if (!fitted) {
                    currentPiece.rotateCounterClockwise();
                }
            }
            panel.repaint();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}

