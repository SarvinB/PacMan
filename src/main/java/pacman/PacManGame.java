package pacman;

import gameEngine.GameEngine;
import gameEngine.level.LevelHandler;

import java.awt.*;
import java.util.Objects;

public class PacManGame extends GameEngine
{
    public static final int START_X = 500;
    public static final int START_Y = 300;
    public static final int PACK_MAN_SIZE = 50;
    private PacMan pacMan;
    private boolean gameStop;
    private boolean gameOver;
    private PackManLevel packManLevel;
    private int eatenSeeds;
    private int width;
    private int height;
    public PacManGame(int width, int height) {
        super(width, height, new Color(33, 33, 33), "/pacman_death.wav", "/game_win_audio.wav", "/pacman_beginning.wav", "/winner.png", "/game_over.png", "/comics.ttf");
        this.width = width;
        this.height = height;
        this.pacMan = new PacMan(this::addGameObject);
//        SeedAdder var10003 = this::addGameObject;
        PacMan var10004 = this.pacMan;
        Objects.requireNonNull(var10004);
        this.setGameListener(new PacManGameListener(this.pacMan, new PacManGameListener.Stopper() {
            public void stop(boolean b) {
                PacManGame.this.gameStop = b;
            }

            public boolean isStop() {
                return PacManGame.this.isGameStop();
            }
        }));
        this.setNextLevelCallback(new NextLevelCallback() {
            public void goToNextLevel() {
                PacManGame.this.finish();
                PacManGame.this.start();
            }

            public void loadNextLevel() {
                LevelHandler.getLevelHandler().goToNextLevel();
            }
        });
    }

    @Override
    protected void updateGame() {

    }

    @Override
    protected boolean isGameWin() {
        return false;
    }

    @Override
    protected boolean isGameOver() {
        return false;
    }

    @Override
    protected boolean isGameStop() {
        return false;
    }

    @Override
    protected void initialGame() {
        this.gameOver = false;
        this.gameStop = false;
        //this.packManLevel = (PackManLevel) LevelHandler.getLevelHandler().getCurrentLevel();
        //this.setDelay(this.packManLevel.getDelay());
    }

    @Override
    public void finish() {

    }
}
