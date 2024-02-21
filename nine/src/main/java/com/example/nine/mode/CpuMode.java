package com.example.nine.mode;

import com.example.nine.game.NormalGame;
import com.example.nine.game.State;

public class CpuMode extends Mode{
        @Override
        public void start() throws Exception {
            this.normalGame = new NormalGame();
            this.mode = "CpuGame";
            State.mode = this.mode;
            normalGame.start(mode);
        }

}
