package main.java.tetris.Model;

import java.util.Random;

public class PieceGenerator {
private String [] types = {"T","I","L","J","O","S","Z"};
    Random random = new Random();
    public Tetromino generatePiece(){
        int index = random.nextInt(types.length);    
        String type = types[index];
        Tetromino piece =  new Tetromino(0,3, type);
        return piece;
    }
}
