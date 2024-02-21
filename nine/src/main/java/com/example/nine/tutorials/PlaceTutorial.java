package com.example.nine.tutorials;


import com.example.nine.game.Actor;

public class PlaceTutorial extends Tutorial {

    public PlaceTutorial(){
        this.endMessage = "You can now place pieces!";
    }

    @Override
    public void start(String tutorialSelected) throws Exception {
        super.start(tutorialSelected);
        setDialog("Place a piece on the board by selecting your piece and clicking on a position.");
    }

    @Override
    public void initialize(Actor playerOne, Actor playerTwo){
    }

    @Override
    public void populateBoard() {}

}
