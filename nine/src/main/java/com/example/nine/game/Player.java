package com.example.nine.game;

import com.example.nine.turn.RemoveMove;
import javafx.event.EventTarget;
import javafx.scene.layout.*;

import static com.example.nine.game.State.*;
public class Player extends Actor {

    public Player(GridPane holder, String player) {
        super(holder, player);
        addEventHandlers(holder);
        this.holder = holder;
    }

    // Event handler for selecting an unplaced piece
    protected void addEventHandlers(GridPane holder){
        holder.setOnMouseClicked(e -> {

            EventTarget target = e.getTarget();

            if (!target.toString().equals("Piece")) return;

            Piece targetPiece = (Piece) target;

            // If still in placing stage and can't remove piece
            if (State.gameStage == GameStage.Placing_STAGE && !RemoveMove.removable) {
                // Placing stage logic
                if (currentPiece != null && targetPiece.getColor().equals(currentPiece.getColor())){
                    currentPiece.deselectPiece();
                }

                if ((targetPiece.getColor().equals("red") && State.isPlayerOneTurn) || (targetPiece.getColor().equals("black") && !State.isPlayerOneTurn)) {
                      currentPiece = targetPiece;
                      currentPiece.selectPiece();

                }
            }

            if ((targetPiece.getColor().equals("red") && State.isPlayerOneTurn) || (targetPiece.getColor().equals("black") && !State.isPlayerOneTurn)) Hint.unglow();
        });
    }
}