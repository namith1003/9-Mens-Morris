package com.example.nine.mode;

import com.example.nine.game.NormalGame;

public abstract class Mode {

    String mode;
    NormalGame normalGame;

    public abstract void start() throws Exception;

}

