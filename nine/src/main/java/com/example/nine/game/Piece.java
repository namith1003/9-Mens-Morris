package com.example.nine.game;


import com.example.nine.turn.RemoveMove;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static com.example.nine.game.State.currentBoardPiece;

public class Piece extends ImageView {

    private final String color;

    public int posX;
    public int posY;

    public boolean inMill;
    private boolean jumpable;

    private boolean unSelectable;

    public Piece(String color, int posX, int posY){
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        setImage();
        this.jumpable = false;
        this.inMill = false;
        this.unSelectable = false;
    }

    public String getColor() {
        return color;
    }

    public String getOppositeColor() {
        if(this.color.equals("red")){
            return "black";
        }
        else if(this.color.equals("black")){
            return "red";
        }
        return color;
    }

    public void setImage(){
        this.setImage(new Image("file:src/main/java/com/example/nine/images/" + color + "Piece.png"));
    }

    @Override
    public String toString() {
        return "Piece";
    }

    // Add select piece effect
    public void selectPiece(){
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.GREEN);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        this.setEffect(borderGlow);
    }

    // Remove select piece effect
    public void deselectPiece(){
        this.setEffect(null);
    }

    public boolean select(){
        // If another piece already selected reassign current piece
        boolean deselect = false;
        if (currentBoardPiece != null && this.getColor().equals(currentBoardPiece.getColor())){
            currentBoardPiece.deselectPiece();
            currentBoardPiece = null;
        }

        // Assign a piece to currentBoardPiece when a piece is selected
        if (!RemoveMove.removable && ((this.getColor().equals("red") && State.isPlayerOneTurn) || (this.getColor().equals("black") && !State.isPlayerOneTurn))) {
            currentBoardPiece = this;
            currentBoardPiece.selectPiece();
            deselect = true;
        }

        return deselect;
    }

    public void setUnSelectable(){
        this.unSelectable = true;
    }

    public boolean isUnSelectable() {
        return unSelectable;
    }

    // Make a piece able to jump
    public void setJumpable(){
        this.jumpable = true;
    }

    // Check if a piece can jump
    public boolean checkJumpable(){
       return this.jumpable;
    }


}
