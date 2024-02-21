package com.example.nine.game;

import com.example.nine.turn.*;
import javafx.scene.layout.StackPane;

import static com.example.nine.game.State.currentBoardPiece;
import static com.example.nine.game.State.currentPiece;

public class Position extends StackPane {

    public int x;
    public int y;
    boolean occupied;



    private Piece piece;
    public String name;


    public Position(int x, int y){
        this.x = x;
        this.y = y;
        this.occupied = false;
    }

    @Override
    public String toString() {
        return "Position";
    }

    public void setName(String name){
        this.name = name;
    }

    public void addPiece(Piece piece){
        this.occupied = true;
        this.piece = piece;
        this.getChildren().add(piece);
    }

    public void removePiece(){
        this.getChildren().removeAll();
        this.piece = null;
        this.occupied = false;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isOccupied(){
        return occupied;
    }

    // Gets rid of piece placed on the board
    public void deletePiece(Piece deletedPiece){
        RemoveMove remove = new RemoveMove();
        remove.move(this, deletedPiece);
        remove.checkIfNoPossibleMoves(deletedPiece.getColor());

    }

    // When player clicks on a position
    public boolean[] select(Player playerOne, Player playerTwo){
        boolean[] deselectMill = new boolean[2];
        boolean deselect;
        boolean millFormed = false;

        // If piece selected and position not occupied
        if ((currentPiece != null) && !this.occupied) {
            Piece selectedPiece = currentPiece;
            PlaceMove placeMove = new PlaceMove();
            // Check if placed piece forms mill
            String color = selectedPiece.getOppositeColor();
            millFormed = placeMove.move(this, selectedPiece);

            placeMove.checkIfNoPossibleMoves(color);

            // Check if red player has unplaced pieces left
            if (selectedPiece.getColor().equals("red")) {

                State.playerOneHolderState = State.PlayerOneHolder.playerOne_EMPTY;
                for(int i = 0; i < playerOne.playerHolder.size(); i++)
                {
                    Position holderPosition = playerOne.playerHolder.get(i);

                    if (!holderPosition.getChildren().isEmpty()){
                        Piece holderPiece = (Piece) holderPosition.getChildren().get(0);
                        if (holderPiece.getColor().equals("red")){
                            State.playerOneHolderState = State.PlayerOneHolder.playerOne_HAVE;
                            break;
                        }
                    }
                }
            }
            // Check if black player has unplaced pieces left
            else if (selectedPiece.getColor().equals("black")){
                State.playerTwoHolderState = State.PlayerTwoHolder.playerTwo_EMPTY;
                for(int i = 0; i < playerTwo.playerHolder.size(); i++)
                {
                    Position holderPosition = playerTwo.playerHolder.get(i);

                    if (!holderPosition.getChildren().isEmpty()){
                        Piece holderPiece = (Piece) holderPosition.getChildren().get(0);
                        if(holderPiece.getColor().equals("black")){
                            State.playerTwoHolderState = State.PlayerTwoHolder.playerTwo_HAVE;
                            break;
                        }

                    }
                }

            }

        }

        // If all pieces placed, change state to normal move state
        if ((State.playerOneHolderState == State.PlayerOneHolder.playerOne_EMPTY) && (State.playerTwoHolderState == State.PlayerTwoHolder.playerTwo_EMPTY)){
            State.gameStage = State.GameStage.NormalMove_STAGE;
        }

        // If player clicks on piece on the board and adjacent position
        if ((currentBoardPiece != null) && !this.occupied) {
            // If piece can't jump
            String color = currentBoardPiece.getOppositeColor();

            if(!currentBoardPiece.checkJumpable()) {
                RegularMove regularMove = new RegularMove();
                millFormed = regularMove.move(this, currentBoardPiece);
                regularMove.checkIfNoPossibleMoves(color);
            }
            // If piece can jump
            else if(currentBoardPiece.checkJumpable()) {
                JumpMove jumpMove = new JumpMove();
                millFormed = jumpMove.move(this, currentBoardPiece);
                jumpMove.checkIfNoPossibleMoves(color);

            }
        }

        if (!millFormed && !State.gameStage.equals(State.GameStage.Placing_STAGE)){
            Turn.increment();
        } else {
            Turn.counter = 0;
        }

        deselect = !millFormed;
        deselectMill[0] = deselect;
        deselectMill[1] = millFormed;

        return deselectMill;
    }

}