package com.example.nine.mode;

import com.example.nine.game.NormalGame;
import com.example.nine.game.State;

public class PlayerMode extends Mode {
    @Override
    public void start() throws Exception {
        this.normalGame = new NormalGame();
        this.mode = "PlayerGame";
        State.mode = this.mode;
        normalGame.start(mode);
    }

}

