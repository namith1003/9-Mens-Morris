package com.example.nine.game;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Mill {

    public final ArrayList<Piece> millPieces = new ArrayList<>();

    // Add a piece to a mill
    public void addToMill(Piece piece){
        millPieces.add(piece);
    }

    public void glowMill(){
        if (millPieces.size() == 3){
            for (Piece millPiece : millPieces) {
                this.inMill(millPiece);
            }
        }
    }

    // Remove mill glow
    public void deGlowMill(){
        for (Piece millPiece : millPieces) {
            millPiece.setEffect(null);
        }

    }

    public int size(){
        return millPieces.size();
    }

    @Override
    public String toString() {
        return "Mill";
    }

    // Put piece in a mill
    public void inMill(Piece piece){
        piece.inMill = true;
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.YELLOW);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        piece.setEffect(borderGlow);
    }

    // Check if piece is in a mill
    public boolean searchMill(Piece piece){
        for(int i = 0; i < millPieces.size(); i++){
             if (millPieces.contains(piece)){
                 return true;
             }
        }
        return false;
    }


}
