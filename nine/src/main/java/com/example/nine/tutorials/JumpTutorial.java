package com.example.nine.tutorials;

public class JumpTutorial extends Tutorial{

    public JumpTutorial(){
        this.endMessage = "You can now jump pieces!";
    }

    @Override
    public void start(String tutorialSelected) throws Exception {
        super.start(tutorialSelected);
        setDialog("You only have 3 pieces left, your pieces are now able to jump anywhere on the board.");
    }

    @Override
    public void populateBoard() {
        // Red positions
        addPosition(5, 1, "red", false, true);
        addPosition(0, 3, "red", true, false);
        addPosition(0, 6, "red", true, false);

        // Black positions
        addPosition(6, 0, "black", true, false);
        addPosition(6, 4, "black", true, false);
        addPosition(6, 6, "black", true, false);
        addPosition(3, 1, "black", true, false);
        addPosition(4, 2, "black", true, false);
        addPosition(5, 3, "black", true, false);
    }
}
