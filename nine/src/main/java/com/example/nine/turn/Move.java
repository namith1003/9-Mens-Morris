package com.example.nine.turn;


import com.example.nine.game.*;

import java.util.ArrayList;

import static com.example.nine.game.Board.layout;
import static com.example.nine.game.State.*;

public abstract class Move {

    public abstract ArrayList<String> findPossibleMoves(int x, int y);

    public boolean move(Position targetPosition, Piece selectedPiece){

        // Find all the possible moves a piece can make
        ArrayList<String> possibleMoves = findPossibleMoves(selectedPiece.posX, selectedPiece.posY);

        if(!possibleMoves.contains(targetPosition.name)) {
            return false;
        }

        boolean millFormed;

        // If piece moves out of mill, break mill
        for (int i = 0; i < Board.mills.size(); i++){
            if (Board.mills.get(i).searchMill(selectedPiece)){
                for (Piece piece: Board.mills.get(i).millPieces){
                    piece.inMill = false;
                }
                Board.mills.remove(Board.mills.get(i));
            }
        }

        // Move the selected piece from current position to target position
        Position startPosition = (Position) selectedPiece.getParent();
        Hint.unglow();
        targetPosition.addPiece(selectedPiece);
        startPosition.removePiece();
        selectedPiece.posX = targetPosition.x;
        selectedPiece.posY = targetPosition.y;
        selectedPiece.deselectPiece();

        currentBoardPiece = null;

        State.getBoardGrid().checkAllMills();

        millFormed = Board.searchPieceInMill(selectedPiece);

        String color;
        if (isPlayerOneTurn){
            color = "red";
        } else {
            color = "black";
        }

        // If mill formed allow user to remove a piece
        if (millFormed){
            // remove a piece

            String millColor;
            if (isPlayerOneTurn){
                millColor = "black";
            } else {
                millColor = "red";
            }

            boolean allEnemyPiecesInMill = true;
            for (Position[] positions : Board.positionsLayout){
                for (Position position:positions){
                    if (position.getPiece() != null && position.getPiece().getColor().equals(millColor) && !position.getPiece().inMill) {
                        allEnemyPiecesInMill = false;
                        break;
                    }
                }
            }

            if (allEnemyPiecesInMill){
                State.swapTurn();
                return true;
            }

            RemoveMove removeMove = new RemoveMove();
            State.setRemovableState(true);
            removeMove.removableGlow();

            // check if opponent has three pieces
            if (currentPiece!= null){
                Board.checkJumpable(currentPiece.getOppositeColor());
            }
            return true;
        }
        // check if there is no more possible moves to play
        checkIfNoPossibleMoves(color);

        currentPiece = null;

        // Swap turns
        State.swapTurn();

        return false;
    }

    // Check if no possible moves can be mad by all pieces of a color
    public void checkIfNoPossibleMoves(String color){
        String winningColor = "";
        boolean gameOver = true;
        for (int row=0; row<layout.length; row++){
            for (int col=0; col<layout[0].length; col++){
                Position position = Board.positionsLayout[row][col];
                Move move;
                if (gameStage == GameStage.Placing_STAGE){
                    move = new PlaceMove();
                } else if (Board.checkJumpable(color)) {
                    move = new JumpMove();
                } else{
                    move = new RegularMove();
                }

                if (position.getPiece() != null && position.getPiece().getColor().equals(color) && !position.getPiece().checkJumpable()){
                    winningColor = position.getPiece().getColor();
                    ArrayList<String> possibleMoves = move.findPossibleMoves(position.x, position.y);
                    if(possibleMoves != null && !possibleMoves.isEmpty()){
                        gameOver = false;
                    }
                }
            }
        }

        if (gameOver && !winningColor.equals("") && !State.mode.equals("TutorialMode")){
            State.endGame(winningColor);
        }

    }
}
