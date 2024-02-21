package com.example.nine.tutorials;

import com.example.nine.controller.Display;
import com.example.nine.game.*;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public abstract class Tutorial extends Game{

    protected String endMessage;


    public void initialize(Actor playerOne, Actor playerTwo){
        clearHolders(playerOne, playerTwo);
        populateBoard();
    }
    public String getEndMessage(){
        return endMessage;
    }

    @Override
    public void perform() throws Exception {
        TutorialMenu menu = new TutorialMenu();
        menu.showMenu();
    }

    public void setDialog(String dialogMessage){
        Label dialogLabel = (Label) Display.getScene().lookup("#hintMessage");
        dialogLabel.setText(dialogMessage);

    }

    public abstract void populateBoard();

    public String getTutorialName(){
        return "tutorialGame";
    }

    public void endTutorial() {
        TutorialPopup.display(getEndMessage());
    }

    public void clearHolders(Actor playerOne, Actor playerTwo){
        for(int i = 0; i < playerOne.playerHolder.size(); i++) {

            Position holderPosition1 = playerOne.playerHolder.get(i);
            if(holderPosition1.isOccupied()) {
                holderPosition1.deletePiece(holderPosition1.getPiece());
            }

            Position holderPosition2 = playerTwo.playerHolder.get(i);
            if(holderPosition2.isOccupied()) {
                holderPosition2.deletePiece(holderPosition2.getPiece());
            }

        }

        State.playerOneHolderState = State.PlayerOneHolder.playerOne_EMPTY;
        State.playerTwoHolderState = State.PlayerTwoHolder.playerTwo_EMPTY;
        State.gameStage = State.GameStage.NormalMove_STAGE;
    }

    public void addPosition(int x, int y, String color, boolean unselectable, boolean jumpAble){
        Position position = Board.positionsLayout[x][y];
        Piece piece = new Piece(color, position.x, position.y);

        if(jumpAble){
            piece.setJumpable();
        }

        if (unselectable){
            piece.setUnSelectable();
        } else if (color.equals("red")){
            DropShadow borderGlow = new DropShadow();
            borderGlow.setColor(Color.YELLOW);
            borderGlow.setOffsetX(0f);
            borderGlow.setOffsetY(0f);
            piece.setEffect(borderGlow);
        }
        position.addPiece(piece);

    }


}
