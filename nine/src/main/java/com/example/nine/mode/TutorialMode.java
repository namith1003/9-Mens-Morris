package com.example.nine.mode;

import com.example.nine.tutorials.TutorialMenu;

public class TutorialMode extends Mode{
        @Override
        public void start() throws Exception {
            TutorialMenu tutorialMenu = new TutorialMenu();
            tutorialMenu.showMenu();
        }

}
