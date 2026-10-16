package main.java.tetris.Model;

public class Board {
 private static final int WIDTH = 10;
    private static final int HEIGHT = 20;
    private String [][] grid ;
    
    public Board() {
        grid = new String [WIDTH][HEIGHT];
    }

    public String [][] getGrid() {
        return grid;
    }

    public boolean isInside(int col , int row){
     return col >= 0 && col < WIDTH && row >= 0 && row < HEIGHT;
    }
    public boolean canMove(Tetromino piece , int newCol , int newRow){
        int [][]blocks = piece.getBlocks();
        for (int[] block : blocks) {
            int col = newCol + block[0];
            int row = newRow + block[1];
            if (col >= WIDTH || col < 0 ) {
                return false;
            }
            if (row >= HEIGHT || row < 0) {
                return false;
            }
            if (grid[col][row]!= null) {
                return false;
            }
        }
        return true;    
    }
    public void lockPiece(Tetromino piece){
        int [][] blocks = piece.getBlocks();
        for (int[] block : blocks) {
            int col = piece.getCol() + block[0];
            int row = piece.getRow() + block[1];
            grid[col][row] = piece.getType();
        }
    }
    public int clearLines(){
        int linesCleared = 0;
        for (int row = HEIGHT - 1; row >= 0; row--){
            if (isLineFull(row)) {
                removeLine(row);
                linesCleared++;
                row++;
            }
        }
        return linesCleared;
    }
    public boolean isLineFull(int row){
        for (int col = 0; col < WIDTH; col ++){
            if (grid[col][row] == null) {
                return false;
            }
        }
        return true;
    }
    public void removeLine(int row){
        for(int currentRow = row; currentRow > 0; currentRow--){
            for (int col = 0; col < WIDTH; col ++){
                grid[col][currentRow] = grid [col][currentRow-1];
            }
        }
        for (int col = 0; col < WIDTH; col ++){
        grid[col][0] = null;
        }
    }
}
