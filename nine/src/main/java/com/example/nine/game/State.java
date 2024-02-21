package com.example.nine.game;

import com.example.nine.controller.EndGamePopup;
import com.example.nine.turn.RemoveMove;
import com.example.nine.turn.Turn;
import com.example.nine.tutorials.Tutorial;
import com.example.nine.tutorials.TutorialLauncher;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class State {
    public static Boolean isPlayerOneTurn;
    public static Turn turn;
    public static String mode;

    @FXML
    Pane playerOneSide;

    @FXML
    Pane playerTwoSide;

    @FXML
    GridPane playerOneHolder;

    @FXML
    GridPane playerTwoHolder;

    @FXML
    GridPane board;

    Actor playerOne, playerTwo;

    public static Pane getPlayerOneSide;
    public static Pane getPlayerTwoSide;


    public static Board boardGrid;

    public static Piece currentPiece;

    public static Piece currentBoardPiece;

    public static GameStage gameStage;

    public static PlayerOneHolder playerOneHolderState;

    public static PlayerTwoHolder playerTwoHolderState;


    // Initialize state, player and board at the beginning of a game
    @FXML
    public void initialize(){

        turn = new Turn();
        isPlayerOneTurn = turn.getIsPlayerOneTurn();

        gameStage = GameStage.Placing_STAGE;
        playerOneHolderState = PlayerOneHolder.playerOne_HAVE;
        playerTwoHolderState = PlayerTwoHolder.playerTwo_HAVE;
        getPlayerOneSide = playerOneSide;
        getPlayerTwoSide = playerTwoSide;

        this.playerOne = new Player(playerOneHolder, "red");

        if (mode.equals("PlayerGame")){
            this.playerTwo = new Player(playerTwoHolder, "black");
            boardGrid = new Board(board, (Player) playerOne, (Player) playerTwo);
        }

        else if (mode.equals("TutorialMode")) {
            this.playerTwo = new Player(playerTwoHolder, "black");
            boardGrid = new Board(board, (Player) playerOne, (Player) playerTwo);
            Tutorial tutorial = TutorialLauncher.tutorial;
            tutorial.initialize(playerOne, playerTwo);

        }

        else {
            this.playerTwo = new Computer(playerTwoHolder, "black");
            boardGrid = new Board(board, (Player) playerOne, (Computer) playerTwo);
        }

        showTurn(playerOneSide, playerTwoSide);
    }

    public static Board getBoardGrid() {
        return boardGrid;
    }

    public static void swapTurn(){
        State.isPlayerOneTurn = !State.isPlayerOneTurn;
        if (isPlayerOneTurn){
            showTurn(getPlayerOneSide, getPlayerTwoSide);
        } else {
            showTurn(getPlayerTwoSide, getPlayerOneSide);
        }
        if(mode.equals("TutorialMode")){
            TutorialLauncher.tutorial.endTutorial();
        }

    }

    public static void showTurn(Pane glowPlayerSide, Pane unGlowPlayerSide){
        // Create a DropShadow effect
        DropShadow glowEffect = new DropShadow();
        glowEffect.setColor(Color.LIGHTBLUE);
        glowEffect.setWidth(20); // Adjust the width of the glow effect
        glowEffect.setHeight(20); // Adjust the height of the glow effect
        glowEffect.setSpread(0.7);
        // Apply the DropShadow effect to the pane's border
        glowPlayerSide.setEffect(glowEffect);
        unGlowPlayerSide.setEffect(null);
    }

    public static void setRemovableState(boolean removable){
        RemoveMove.removable = removable;
    }

    // Check if less than 3 pieces on board
    private static boolean lessThanThree(String color){
        int pieceCount = 0;
        for (int row=0; row<7; row++){
            for (int col=0; col<7; col++){
                Position position = Board.positionsLayout[row][col];
                if (position.getPiece() != null && position.getPiece().getColor().equals(color)){
                    pieceCount += 1;
                }
            }
        }
        return pieceCount < 3;
    }

    // Check if game should be ended
    static void checkGameOver(String color){
        if (lessThanThree(color) && State.gameStage == State.GameStage.NormalMove_STAGE && !mode.equals("TutorialMode")){
            endGame(color);
        }
    }

    //  End game
    public static void endGame(String color){
        State.gameStage = GameStage.GameEnd_STAGE;
        if (color.equals("red")) {
            EndGamePopup.setWinningColor("black");
        }
        else if (color.equals("black")) {
            EndGamePopup.setWinningColor("red");
        }
        EndGamePopup.display();
    }

    // Enum for different states of a game
    public enum GameStage {
        Placing_STAGE,
        NormalMove_STAGE,
        GameEnd_STAGE
    }

    public enum PlayerOneHolder{
        playerOne_HAVE,
        playerOne_EMPTY
    }

    public enum PlayerTwoHolder{

        playerTwo_HAVE,
        playerTwo_EMPTY
    }

}


