package com.example.nine.game;

import com.example.nine.controller.Display;
import com.example.nine.controller.Menu;

public abstract class Game {

    public void start(String mode) throws Exception {
        Display.switchTo(mode, 940, 730);
    }

    public void perform() throws Exception {
        Menu menu = new Menu();
        menu.showMenu();
    }


}