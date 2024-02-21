package com.example.nine.tutorials;


public class TwoPiecesLeftTutorial extends Tutorial {

    public TwoPiecesLeftTutorial(){
        this.endMessage = "You win, opponent only has 2 pieces left!";
    }

    @Override
    public void start(String tutorialSelected) throws Exception {
        super.start(tutorialSelected);
        setDialog("The game ends when a player has less than three pieces left, remove a piece from the opponent.");
    }

    @Override
    public void populateBoard(){

        // Red positions
        addPosition(3, 0, "red", false, false);
        addPosition(0, 3, "red", true, false);
        addPosition(0, 6, "red", true, false);
        addPosition(1, 5, "red", true, false);

        // Black positions
        addPosition(3, 1, "black", true, false);
        addPosition(1, 3, "black", true, false);
        addPosition(6, 0, "black", true, false);

    }
}
