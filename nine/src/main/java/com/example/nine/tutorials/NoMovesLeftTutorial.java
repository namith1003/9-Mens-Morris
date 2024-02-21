package com.example.nine.tutorials;

public class NoMovesLeftTutorial extends Tutorial{

    public NoMovesLeftTutorial(){
        this.endMessage = "You win, opponent has no moves left!";
    }

    @Override
    public void start(String tutorialSelected) throws Exception {
        super.start(tutorialSelected);
        setDialog("The game can also end when a player has no valid moves left, block off your opponent.");
    }

    @Override
    public void populateBoard() {
        // Red positions
        addPosition(6, 0, "red", true, false);
        addPosition(3, 1, "red", true, false);
        addPosition(4, 2, "red", true, false);
        addPosition(1, 3, "red", true, false);
        addPosition(5, 3, "red", true, false);
        addPosition(2, 4, "red", true, false);
        addPosition(3, 6, "red", true, false);
        addPosition(5, 5, "red", false, false);
        addPosition(6, 6, "red", true, false);

        // Black positions
        addPosition(1, 1, "black", true, false);
        addPosition(5, 1, "black", true, false);
        addPosition(2, 2, "black", true, false);
        addPosition(3, 2, "black", true, false);
        addPosition(2, 3, "black", true, false);
        addPosition(4, 3, "black", true, false);
        addPosition(3, 4, "black", true, false);
        addPosition(4, 4, "black", true, false);
        addPosition(1, 5, "black", true, false);
    }

}
