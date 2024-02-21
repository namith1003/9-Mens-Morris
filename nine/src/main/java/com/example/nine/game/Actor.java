package com.example.nine.game;

import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public abstract class Actor {

    public GridPane holder;

    public static String playerColor;
    public int numOfPieces = 9;

    public final ArrayList<Position> playerHolder = new ArrayList<>();

    public Actor(GridPane holder, String player){
        this.holder = holder;
        playerColor = player;
        makeHolder(holder);
    }

    abstract void addEventHandlers(GridPane holder);

    // Add holder for unplaced pieces
    private void makeHolder(GridPane holder){
        for(int col=0; col<9; col++){
            Position position = new Position(0, col);
            holder.add(position, 0, col, 1, 1);
            playerHolder.add(position);
        }
        addPieces(playerHolder);
    }

    // Add a piece to a postion
    public void addPiece(Position position){

        Piece piece = new Piece(playerColor, position.x, position.y);
        position.addPiece(piece);
        position.occupied = true;
    }

    // Add unplaced pieces to player holder
    private void addPieces(ArrayList<Position> playerHolder){
        for(Position position : playerHolder){
            if (position.occupied) continue;
            addPiece(position);
        }
    }

    // Reduce number of pieces
    public void cutPiece(){
        numOfPieces = numOfPieces - 1;
    }


}