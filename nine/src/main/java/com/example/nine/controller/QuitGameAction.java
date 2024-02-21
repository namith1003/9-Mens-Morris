package com.example.nine.controller;

import com.example.nine.controller.Action;
import com.example.nine.controller.Application;
import com.example.nine.game.State;

public class QuitGameAction extends Action {

    @Override
    public void perform(){
        Application.window.close();
    }

}

