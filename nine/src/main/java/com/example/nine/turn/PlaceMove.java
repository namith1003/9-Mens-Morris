package com.example.nine.turn;

import com.example.nine.game.Board;

import java.util.ArrayList;

import static com.example.nine.game.Board.layout;
import static com.example.nine.game.Board.positionsLayout;

public class PlaceMove extends Move {

    // Piece can be placed anywhere on the board
    @Override
    public ArrayList<String> findPossibleMoves(int x, int y) {
        ArrayList<String> moves = new ArrayList<>();
        int nRows = layout.length;
        int nCols = layout[0].length;

        // Add all the position on the board
        for (int row=0; row<nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                if (layout[row][col] == 1 && !Board.positionsLayout[row][col].isOccupied()) {
                    moves.add("Position" + (row) + (col));
                }
            }
        }
        return moves;
    }
}

