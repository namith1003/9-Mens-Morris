package com.example.nine.tutorials;

import com.example.nine.controller.Menu;
import com.example.nine.game.State;

public class TutorialLauncher {
    private final String mode = "TutorialMode";
    public static Tutorial tutorial;
    public void placeTutorial() throws Exception {
        State.mode = this.mode;
        tutorial = new PlaceTutorial();
        tutorial.start(tutorial.getTutorialName());
    }

    public void millTutorial() throws Exception {
        State.mode = this.mode;
        tutorial = new MillTutorial();
        tutorial.start(tutorial.getTutorialName());
    }

    public void jumpTutorial() throws Exception {
        State.mode = this.mode;
        tutorial = new JumpTutorial();
        tutorial.start(tutorial.getTutorialName());
    }

    public void twoPiecesLeftTutorial() throws Exception {
        State.mode = this.mode;
        tutorial = new TwoPiecesLeftTutorial();
        tutorial.start(tutorial.getTutorialName());
    }

    public void noMovesLeftTutorial() throws Exception {
        State.mode = this.mode;
        tutorial = new NoMovesLeftTutorial();
        tutorial.start(tutorial.getTutorialName());
    }

    public void back() throws Exception {
        Menu menu = new Menu();
        menu.showMenu();
    }
}
