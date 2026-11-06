package main.java.tetris.Engine;

import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import main.java.tetris.Model.Board;
import main.java.tetris.Model.Tetromino;
import main.java.tetris.Ui.GamePanel;

public class GameLoop implements ActionListener {
  private Timer timer;
    private GamePanel panel;
    private Board board;
    public GameLoop(GamePanel panel,Board board) {
        this.panel = panel;
        this.board = board;
        timer = new Timer(350, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Tetromino currentPiece = panel.getCurrentPiece();
        if (board.canMove(currentPiece, currentPiece.getCol(), currentPiece.getRow()+1)) {
            currentPiece.moveDown();
        }
        else{
            board.lockPiece(currentPiece);
            board.clearLines();
            panel.generateNewPiece();
            Tetromino newPiece = panel.getCurrentPiece();
            if (!board.canMove(newPiece, newPiece.getCol(), newPiece.getRow())) {
                timer.stop();
                System.out.println("GAME OVER");
            }
        }
        panel.repaint();
    }
    
        
}

