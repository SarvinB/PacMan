package com.company;

import gameUI.GameFrame;
import gameUI.GameHome;
import pacman.PacManGame;

public class Main {

    public static void main(String[] args) {
        GameHome gameHome = new GameHome("/background.jpg",
                "/run.png",
                "/run_clicked.png",
                1000, 675);
        PacManGame game = new PacManGame(1000, 675);
        GameFrame frame = new GameFrame(1000, 675, game, gameHome);
    }
}
