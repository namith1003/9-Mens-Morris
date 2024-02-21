package com.example.nine.turn;

import com.example.nine.game.Piece;
import com.example.nine.game.Position;
import com.example.nine.game.State;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static com.example.nine.game.Board.positionsLayout;

public class RemoveMove extends Move {

    public static boolean removable = false;

    // Add red glow around pieces that can be removed after mill is formed
    public void removableGlow(){

        for (int x = 0; x < 7; x ++){
            for (int y = 0; y < 7; y++){
                if (positionsLayout[x][y].getPiece() != null && State.isPlayerOneTurn  && !positionsLayout[x][y].getPiece().inMill){
                    if (!positionsLayout[x][y].getPiece().getColor().equals("red")){
                        this.setRemoveEffect(positionsLayout[x][y].getPiece());
                    }
                } else {
                    if (positionsLayout[x][y].getPiece() != null && !State.isPlayerOneTurn && !positionsLayout[x][y].getPiece().inMill ){
                        if (!positionsLayout[x][y].getPiece().getColor().equals("black")){
                            this.setRemoveEffect(positionsLayout[x][y].getPiece());
                        }
                    }
                }

            }
        }
    }



    // Add red glow around one piece
    public void setRemoveEffect(Piece piece){
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.RED);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        piece.setEffect(borderGlow);
    }

    @Override
    public ArrayList<String> findPossibleMoves(int x, int y) {
        return null;
    }

    // If player performs remove piece move on a piece, piece is removed from board
    @Override
    public boolean move(Position startPosition, Piece selectedPiece) {

        // Move the selected piece from current position to target position
        startPosition.removePiece();
        selectedPiece.setImage(null);
        selectedPiece.deselectPiece();
        return false;
    }
}
