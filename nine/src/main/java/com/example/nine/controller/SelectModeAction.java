package com.example.nine.controller;

import com.example.nine.mode.CpuMode;
import com.example.nine.mode.Mode;
import com.example.nine.mode.PlayerMode;
import com.example.nine.mode.TutorialMode;
import javafx.event.ActionEvent;

public class SelectModeAction extends Action {

    Mode selectedMode;
    public void playerMode() throws Exception {
        selectedMode = new PlayerMode();
        selectedMode.start();
    }

    public void computerMode() throws Exception {
        selectedMode = new CpuMode();
        selectedMode.start();
    }

    public void tutorialMode() throws Exception {
        selectedMode = new TutorialMode();
        selectedMode.start();
    }

}
