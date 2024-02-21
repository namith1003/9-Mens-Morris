package com.example.nine.game;

import com.example.nine.turn.*;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Hint {

    private static List<String> possibleMoves = new ArrayList<>();
    public void showHints(){
        Piece selectedPiece;
        if (State.currentPiece != null){
            selectedPiece = State.currentPiece;
        } else if (State.currentBoardPiece != null){
            selectedPiece = State.currentBoardPiece;
        } else {
            return;
        }

       possibleMoves = allPossibleMoves(selectedPiece);

        if (!possibleMoves.isEmpty()){
            for (Position[] positions : Board.positionsLayout) {
                for (Position position : positions) {
                    if (possibleMoves.contains(position.name) && position.getPiece() == null){
                        glow(position);
                    }
                }

            }
        }
    }

    public static List<String> allPossibleMoves(Piece selectedPiece){
        List<String> possibleMoves = new ArrayList<>();
        // check for all types of moves which are possible to be performed
        Move regularMove = new RegularMove();
        Move placeMove = new PlaceMove();
        Move jumpMove = new JumpMove();


        if (State.gameStage == State.GameStage.Placing_STAGE){
            possibleMoves.addAll(placeMove.findPossibleMoves(selectedPiece.posX, selectedPiece.posY));
            Computer.move = placeMove;
        } else if (State.gameStage == State.GameStage.NormalMove_STAGE) {
            if (selectedPiece.checkJumpable()){
                possibleMoves.addAll(jumpMove.findPossibleMoves(selectedPiece.posX, selectedPiece.posY));
                Computer.move = jumpMove;
            } else {
                possibleMoves.addAll(regularMove.findPossibleMoves(selectedPiece.posX, selectedPiece.posY));
                Computer.move = regularMove;
            }

        }

        return (possibleMoves);
    }

    public void glow(Position position){
        if (position.getEffect() == null) {
            //Instantiating the Glow class
            Circle circle = new Circle(10);
            circle.setFill(Color.GREEN);
            position.getChildren().add(circle);

            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.GREEN);
            dropShadow.setRadius(3);
            dropShadow.setSpread(0.7);

            position.setEffect(dropShadow);
        }

    }

    public static void unglow() {
        for (Position[] positions : Board.positionsLayout) {
            for (Position position : positions) {
                if (possibleMoves.contains(position.name)) {
                    // Remove circles added in the glow() method
                    for (Node child : position.getChildren()) {
                        if (child instanceof Circle) {
                            position.getChildren().remove(child);
                            break; // Break the loop after removing the circle
                        }
                    }

                    position.setEffect(null);
                }
            }
        }

        possibleMoves = new ArrayList<>();
    }
}
