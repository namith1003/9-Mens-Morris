package com.example.nine.turn;

import com.example.nine.controller.EndGamePopup;

public class Turn {

    public static int counter = 0;
    private final Boolean isPlayerOneTurn;
    public Turn() {
        isPlayerOneTurn = true;
    }

    public Boolean getIsPlayerOneTurn() {
        return isPlayerOneTurn;
    }

    public static void increment(){
        counter += 1;
        if (counter == 24){
            EndGamePopup.display();
        }
    }

}
