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
        if (panel.isGameOver() || panel.isPaused()) {
            return ;
        }
        Tetromino currentPiece = panel.getCurrentPiece();
        if (board.canMove(currentPiece, currentPiece.getCol(), currentPiece.getRow()+1)) {
            currentPiece.moveDown();
        }
        else{
            board.lockPiece(currentPiece);
            int lines = board.clearLines();
            panel.addScore(Board.getScore(lines));
            timer.setDelay(Math.max(100, 350 - (panel.getScore() / 500) * 20));
            panel.generateNewPiece();
            Tetromino newPiece = panel.getCurrentPiece();
            if (!board.canMove(newPiece, newPiece.getCol(), newPiece.getRow())) {
                timer.stop();
                panel.setGameOver(true);
            }
        }
        panel.repaint();
    }
    public void restart() {
        timer.stop();
        timer.setDelay(350);
        timer.start();
    }
    public void pause() {
        timer.stop();
    }

    public void resume() {
        timer.start();
    }
        
}

