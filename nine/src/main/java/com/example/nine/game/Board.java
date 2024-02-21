package com.example.nine.game;

import com.example.nine.turn.RemoveMove;
import javafx.event.EventTarget;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import static com.example.nine.game.State.*;

public class Board {

    GridPane board;
    Player playerOne;
    Player playerTwo;

    Computer computer;

    public static ArrayList<Mill> mills = new ArrayList<>();


    public final static int[][] layout = {
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 0, 1, 0, 1, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 1, 0, 1, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
    };

    public final static Position[][] positionsLayout = new Position[7][7];



    public Board(GridPane board, Player playerOne, Player playerTwo){
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        buildBoard(this.board);
        addEventHandlers(this.board);

    }

    public Board(GridPane board, Player playerOne, Computer playerTwo){
        this.board = board;
        this.playerOne = playerOne;
        this.computer = playerTwo;
        buildBoard(this.board);
        addEventHandlers(this.board);

    }

    // Add positions to board at the start of the game
    private void buildBoard(GridPane board){

        for (int row=0; row<layout.length; row++){
            for (int col=0; col<layout[0].length; col++){
                Position position = new Position(row, col);
                position.setName("Position" + row + col);
                position.setPrefHeight(100);
                position.setPrefWidth(100);
                board.add(position, row, col, 1, 1);
                positionsLayout[row][col] = position;
            }
        }
    }

    // Event handler when user clicks on position or piece on board
    private void addEventHandlers(GridPane board) {
        board.setOnMouseClicked(e -> {
            boolean[] deselectMill = new boolean[2];
            EventTarget target = e.getTarget();
            String player = null;

            if (target.toString().equals("Piece")){
                Piece targetPiece = (Piece) target;

                    if (isPlayerOneTurn && targetPiece.getColor().equals("black")) {
                        player = "red";

                    } else if (!isPlayerOneTurn && targetPiece.getColor().equals("red")) {
                        player = "black";
                    }

                    if ((RemoveMove.removable && player == null)) {
                        return;
                    }

            }

            // click the position and perform relevant code
            if (target.toString().equals("Position") || target instanceof Circle) {


                if ((currentPiece == null && currentBoardPiece == null && !RemoveMove.removable) || (RemoveMove.removable && player == null) || (currentBoardPiece != null && currentBoardPiece.isUnSelectable())){
                    return;
                }

                for (Mill mills: Board.mills){
                    mills.deGlowMill();
                }
                // remove all effects of pieces
                removeEffect();

                if (target instanceof Circle){
                    target = ((Circle) target).getParent();
                }

                Position targetPosition = (Position) target;

                    try {
                        deselectMill = targetPosition.select(playerOne, playerTwo);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

            }
            // else if a piece is clicked perform relevant code
            else if (target.toString().equals("Piece") && ((State.gameStage == State.GameStage.NormalMove_STAGE) || RemoveMove.removable)) {

                Piece targetPiece = (Piece) target;

                for (Mill mills: Board.mills){
                    mills.deGlowMill();
                }

                // remove all effects of pieces
                removeEffect();

                // if the current move is a remove one
                if (RemoveMove.removable && !mode.equals("CpuGame") || RemoveMove.removable && isPlayerOneTurn){

                    if (isPlayerOneTurn && (targetPiece.getColor().equals("black") && !targetPiece.inMill)){
                        Position piecePosition = (Position) targetPiece.getParent();
                        try {
                            piecePosition.deletePiece(targetPiece);

                            if (mode.equals("CpuGame")){
                                computer.cutPiece();
                            } else {
                                playerTwo.cutPiece();
                            }

                        } catch (Exception ex) {
                            throw new RuntimeException(ex); 
                        }

                    } else if (!isPlayerOneTurn && targetPiece.getColor().equals("red") && !targetPiece.inMill){
                        Position piecePosition = (Position) targetPiece.getParent();
                        try {
                            piecePosition.deletePiece(targetPiece);
                            playerOne.cutPiece();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        return;
                    }
                    State.checkGameOver(targetPiece.getColor());

                    State.swapTurn();

                }

                for (int i = 0; i < Board.mills.size(); i++){
                    for (int y = 0; y < 3; y ++){
                        Board.mills.get(i).millPieces.get(y).inMill = false;
                    }
                }


                Board.mills = new ArrayList<>();
                checkAllMills();

                // else the de select will be the return of the target piece
                deselectMill[0] = targetPiece.select();

                //remove move is turned off
                State.setRemovableState(false);

                if ((targetPiece.getColor().equals("red") && State.isPlayerOneTurn) || (targetPiece.getColor().equals("black") && !State.isPlayerOneTurn)) Hint.unglow();
            }


            // remove the select green glow and deselect the piece after turn is done.
            if (currentPiece != null && deselectMill[0]){
                currentPiece.deselectPiece();
                currentPiece = null;
            }

            // don't remove the new yellow glow but deselect the piece after turn is done.
            if (currentPiece != null && deselectMill[1]){
                currentPiece = null;
            }

            if (mode.equals("CpuGame") && !RemoveMove.removable && !isPlayerOneTurn){
                computer.run();
                removeEffect();
                Board.mills = new ArrayList<>();
                checkAllMills();
            }

        });
    }


    public void removeEffect(){
        for (int x = 0; x < 7; x++){
            for (int y = 0; y < 7; y++){
                if (Board.positionsLayout[x][y].getPiece() != null){
                    Board.positionsLayout[x][y].getPiece().setEffect(null);
                }
            }
        }
    }

    // Check mills for both players
    public void checkAllMills(){
        this.checkMills("red");
        this.checkMills("black");
    }

    // Check if mill is formed on the board
    public void checkMills(String color){
        Mill mill;

        for (int y = 0; y < 7; y++) {
            mill = new Mill();

            if (y == 3){
                this.loopRow(4, 7, y, color, mill);
                this.addMill(mill);

                mill = new Mill();

                this.loopRow(0, 3, y, color, mill);
                this.addMill(mill);

                continue;
            }
            this.loopRow(0, 7, y, color, mill);
            this.addMill(mill);

        }

        for (int x = 0; x < 7; x++) {
            mill = new Mill();

            if (x == 3){
                this.loopCol(4, 7, x, color, mill);
                this.addMill(mill);

                mill = new Mill();

                this.loopCol(0, 3, x, color, mill);
                this.addMill(mill);

                continue;
            }
            this.loopCol(0, 7, x, color, mill);
            this.addMill(mill);

        }

    }

    public void loopCol(int start, int end, int row, String color, Mill mill){
        for (int y = start; y < end; y++){
            if (Board.positionsLayout[row][y].getPiece() != null && Board.positionsLayout[row][y].getPiece().getColor().equals(color)){
                mill.addToMill(Board.positionsLayout[row][y].getPiece());
            }
        }
    }


    public void loopRow(int start, int end, int col, String color, Mill mill){
        for (int x = start; x < end; x++){
            if (Board.positionsLayout[x][col].getPiece() != null && Board.positionsLayout[x][col].getPiece().getColor().equals(color)){
                mill.addToMill(Board.positionsLayout[x][col].getPiece());
            }
        }
    }

    // Add mill to list of mills when mill created
    public void addMill(Mill mill){
        if (mill.size() == 3){
            mill.glowMill();
            Board.mills.add(mill);
            for (Piece piece: mill.millPieces){
                piece.inMill = true;
            }
        }
    }

    public void removeMill(Mill mill){
        mills.remove(mill);
    }

    // Check if piece in mill
    public static boolean searchPieceInMill(Piece piece){
        for (Mill mill : mills) {
            if (mill.searchMill(piece)) {
                return true;
            }
        }
        return false;
    }

    // Check if 3 pieces on board
    private boolean equalsThree(String color){
        int pieceCount = 0;
        for (int row=0; row<layout.length; row++){
            for (int col=0; col<layout[0].length; col++){
                Position position = Board.positionsLayout[row][col];
                if (position.getPiece() != null && position.getPiece().getColor().equals(color)){
                    pieceCount += 1;
                }
            }
        }
        return pieceCount == 3;
    }

    // Check if a piece can jump
    public static boolean checkJumpable(String color) {
        if(boardGrid.equalsThree(color)) {
            for (int row = 0; row < layout.length; row++) {
                for (int col = 0; col < layout[0].length; col++) {
                    Position position = Board.positionsLayout[row][col];

                    Piece piece = position.getPiece();
                    if (piece != null && piece.getColor().equals(color)) {
                        piece.setJumpable();
                    }
                }
            }
            return true;
        }
        return false;
    }

}