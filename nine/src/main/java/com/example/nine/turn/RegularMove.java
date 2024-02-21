package com.example.nine.turn;

import java.util.ArrayList;

import static com.example.nine.game.Board.layout;
import static com.example.nine.game.Board.positionsLayout;

public class RegularMove extends Move {

    // Check all neighbouring positions to piece
    @Override
    public ArrayList<String> findPossibleMoves(int x, int y) {
        ArrayList<String> moves = new ArrayList<>();

        // Search in the horizontal direction (left)
        for (int col = y-1; col >= 0; col--) {
            if ((col == 3 && x ==3) || positionsLayout[x][col].isOccupied()){
                break;
            }
            if (layout[x][col] == 1) {
                moves.add("Position" + (x) + (col));
                break;
            }
        }

        // Search in the horizontal direction (right)
        for (int col = y+1; col<layout[x].length; col++) {
            if ((col == 3 && x ==3) || positionsLayout[x][col].isOccupied()){
                break;
            }
            if (layout[x][col] == 1) {
                moves.add("Position" + (x) + (col));
                break;
            }
        }

        // Search in the vertical direction (top)
        for (int row = x-1; row >= 0; row--) {
            if ((row == 3 && y ==3) || positionsLayout[row][y].isOccupied()){
                break;
            }
            if (layout[row][y] == 1) {
                moves.add("Position" + (row) + (y));
                break;
            }
        }

        // Search in the vertical direction (bottom)
        for (int row = x+1; row < layout.length; row++) {
            if ((row == 3 && y ==3) || positionsLayout[row][y].isOccupied()){
                break;
            }
            if (layout[row][y] == 1) {
                moves.add("Position" + (row) + (y));
                break;
            }
        }
        return moves;
    }
}


