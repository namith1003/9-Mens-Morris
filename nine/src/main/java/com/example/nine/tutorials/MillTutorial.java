package com.example.nine.tutorials;

public class MillTutorial extends Tutorial {

    public MillTutorial(){
        this.endMessage = "You can now form mills!";
    }

    @Override
    public void start(String tutorialSelected) throws Exception {
        super.start(tutorialSelected);
        setDialog("Form a mill by placing 3 pieces in a row (diagonals do not count!) and remove an opposition piece.");
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
        addPosition(3, 5, "black", true, false);
        addPosition(6, 0, "black", true, false);
        addPosition(6, 3, "black", true, false);
        addPosition(5, 3, "black", true, false);
        addPosition(5, 5, "black", true, false);

    }
}
