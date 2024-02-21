package com.example.nine.game;

import com.example.nine.turn.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.nine.game.Board.positionsLayout;
import static com.example.nine.game.State.*;

public class Computer extends Actor {
    private int counter = 0;
    private Piece selectedPiece;
    public static Move move;

    public Computer(GridPane holder, String player) {
        super(holder, player);
        addEventHandlers(holder);
        this.holder = holder;
    }

    @Override
    void addEventHandlers(GridPane holder) {
    }

    public void run(){

        // If still in placing stage and can't remove piece
        if (State.gameStage == GameStage.Placing_STAGE && !RemoveMove.removable) {
            currentPiece = computerSelectRandomHolderPiece(holder);
            selectedPiece = currentPiece;
            if (currentPiece != null){
                currentPiece.selectPiece();
            }
        } else {
            currentBoardPiece = computerSelectRandomBoardPiece();
            selectedPiece = currentBoardPiece;
        }

        randomMove();

        // If all pieces placed, change state to normal move state
        if (checkHolderEmpty()){
            State.gameStage = State.GameStage.NormalMove_STAGE;
        }

        if (RemoveMove.removable){
            randomRemove();
        }

        State.checkGameOver("red");


    }

    // Method for the computer to autonomously select a random piece from the holder
    protected Piece computerSelectRandomHolderPiece(GridPane holder) {
        ObservableList<Node> positions = holder.getChildren();

        // Select a random piece from the list
        if (!positions.isEmpty()) {

            Position randomPosition = (Position) positions.get(counter);
            counter += 1;

            // Perform actions with the randomly selected piece
            // For example, you can call a method on the randomPiece object to perform the desired action
            return randomPosition.getPiece();
        }
       return null;
    }

    protected Piece computerSelectRandomBoardPiece() {
        // Create a list to store the positions with black pieces
        List<Position> blackPositions = new ArrayList<>();

        // Iterate over the positionList to find positions with black pieces
        for (Position[] positions : Board.positionsLayout) {
            for (Position position : positions) {
                Piece piece = position.getPiece();

                // Check if the position has a piece and the piece color is "black"
                if (piece != null && piece.getColor().equals("black") && !Hint.allPossibleMoves(piece).isEmpty()) {
                    blackPositions.add(position);
                }
            }
        }

        // Randomly select a position from the blackPositions list
        if (!blackPositions.isEmpty()) {
            int randomIndex = new Random().nextInt(blackPositions.size());
            Position randomPosition = blackPositions.get(randomIndex);

            // Perform actions with the randomly selected position
            return randomPosition.getPiece();
        }

        return null;
    }

    protected void randomMove() {
        List<String> possibleMoves;
        possibleMoves = Hint.allPossibleMoves(selectedPiece);

        if (!possibleMoves.isEmpty()){
            int randomIndex = new Random().nextInt(possibleMoves.size());
            String randomMove = possibleMoves.get(randomIndex);

            for (Position[] positions : Board.positionsLayout) {
                for (Position position : positions) {
                    if (position.name.equals(randomMove)){
                        move.move(position, selectedPiece);
                    }
                }

            }
        }

    }

    private boolean checkHolderEmpty(){
        boolean empty = true;
        State.playerTwoHolderState = State.PlayerTwoHolder.playerTwo_EMPTY;
        for (Position holderPosition : this.playerHolder) {
            if (!holderPosition.getChildren().isEmpty()) {
                Piece holderPiece = (Piece) holderPosition.getChildren().get(0);
                if (holderPiece.getColor().equals("black")) {
                    State.playerTwoHolderState = PlayerTwoHolder.playerTwo_HAVE;
                    empty = false;
                    break;
                }
            }
        }

        return empty;
    }

    protected void randomRemove() {
        List<Position> deletePositions = new ArrayList<>();
        for (int x = 0; x < 7; x ++){
            for (int y = 0; y < 7; y++){
                if (positionsLayout[x][y].getPiece() != null && !positionsLayout[x][y].getPiece().inMill && positionsLayout[x][y].getPiece().getColor().equals("red")){
                    deletePositions.add(positionsLayout[x][y]);
                }
            }
        }

        Random random = new Random();
        int index = random.nextInt(deletePositions.size());
        deletePositions.get(index).deletePiece(deletePositions.get(index).getPiece());
        RemoveMove.removable = false;

        State.swapTurn();
    }



}