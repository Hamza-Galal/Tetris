package main.java.tetris.Model;

public class Tetromino {
private int row;
    private int col;
    private String type;
    private int rotation = 0;
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
    private int[][] getBaseBlocks(){
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
        public int[][] getBlocks() {
        int[][] blocks = getBaseBlocks();
        for (int i = 0; i < rotation; i++) {
            blocks = rotateBlocks(blocks);
        }
        return blocks;
    }

    private int[][] rotateBlocks(int[][] blocks) {
        int maxY = 0;
        for (int[] b : blocks) {
            maxY = Math.max(maxY, b[1]);
        }
        int[][] result = new int[blocks.length][2];
        for (int i = 0; i < blocks.length; i++) {
            result[i][0] = maxY - blocks[i][1];
            result[i][1] = blocks[i][0];
        }
        return result;
    }

    public void rotateClockwise() {
        rotation = (rotation + 1) % 4;
    }

    public void rotateCounterClockwise() {
        rotation = (rotation + 3) % 4;
    }
       public void moveLeft() {
        col--;
    }

    public void moveRight() {
    col++;
    }

    public void moveDown() {
    row++;
    }
}
