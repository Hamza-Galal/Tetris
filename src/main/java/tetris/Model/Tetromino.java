package main.java.tetris.Model;

public class Tetromino {
private int row;
    private int col;
    private String type;
    public Tetromino(int row, int col , String type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int [][]getBlocks(){
        switch (type) {
            case "T":
                return new int[][]{
                    {0,0},{1,0},{2,0},{1,1}
                };
            case "I":
                return new int[][]{
                    {0,0},{0,1},{0,2},{0,3}
                };
            case "O":
                return new int[][]{
                    {0,0},{1,0},{1,1},{0,1}
                };
            case "L":
                return new int[][]{
                    {0,0},{0,1},{0,2},{1,2}
                };
            case"S":
            return new int[][]{
                {1,0},{2,0},{0,1},{1,1}
                };
            case"Z":
            return new int[][]{
                {0,0},{1,0},{1,1},{2,1}
                };
            case"J":
            return new int[][]{
                {2,0},{2,1},{2,2},{1,2}
            };
           
        }
         return new int[0][0];
    }
    public void moveLeft() {
        if (col > 0 ) {
            col--;
        }
    
    }

    public void moveRight() {
    col++;
    }

    public void moveDown() {
    row++;
    }
}
