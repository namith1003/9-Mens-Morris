package com.example.nine.controller;

public class Menu {

    protected int height,width;
    protected String page;

    public Menu(){
        height = 590;
        width = 940;
        page = "menu";
    }

    public void showMenu() throws Exception {
        Display.switchTo(page, width, height);
    }

}
