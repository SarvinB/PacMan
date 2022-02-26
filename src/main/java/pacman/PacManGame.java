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
    private SeedGenerator seedGenerator;
    private int eatenSeeds;
    private int width;
    private int height;
    public PacManGame(int width, int height) {
        super(width, height, new Color(33, 33, 33), "/pacman_death.wav", "/game_win_audio.wav", "/pacman_beginning.wav", "/winner.png", "/game_over.png", "/comics.ttf");
        this.width = width;
        this.height = height;
        this.pacMan = new PacMan(this::addGameObject);
        seedGenerator = new SeedGenerator(this::addGameObject, pacMan::pacManPositions, getWidth(), getHeight());
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
    protected void updateGame()
    {
        if (pacMan.hasHeadCollisionWithWall() || pacMan.isPacManCharOut(width, height))
            gameOver = true;

        if (seedGenerator.checkSeedCollision(pacMan.getPacManChar().getX(), pacMan.getPacManChar().getY())) {
            seedGenerator.changeSeedPosition();
            pacMan.addPoint();
            eatenSeeds++;
        }
    }

    @Override
    protected boolean isGameWin()
    {
        return eatenSeeds == 5;
    }

    @Override
    protected boolean isGameOver() {
        return gameOver;
    }

    @Override
    protected boolean isGameStop() {
        return gameStop;
    }

    @Override
    protected void initialGame() {
        seedGenerator.initial();
        pacMan.initial();
        gameOver = false;
        gameStop = false;
//        packManLevel = LevelHandler.<PackManLevel>getLevelHandler().getCurrentLevel();
//        setDelay(packManLevel.getDelay());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }

    @Override
    public void finish() {

    }
}
