package pacman;

import gameEngine.GameEngine;
import gameEngine.level.LevelHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class PacManGame extends GameEngine
{
    public static final int START_X = 500;
    public static final int START_Y = 300;
    public static final int PACK_MAN_SIZE = 40;
    private PacMan pacMan;
    private boolean gameStop;
    private boolean gameOver;
    private PackManLevel packManLevel;
    private SeedGenerator seedGenerator;
    private ArrayList<Integer> listOfIndex = new ArrayList<>();
    private int eatenSeeds;
    private int width;
    private int height;

    private int xForShowDetail = 340;
    private int yForShowDetail = 15;
    private short[] screenData;
    private final Color dotColor = new Color(192, 192, 0);
    private Color mazeColor;
    private final int N_BLOCKS = 15;
    private int seedNumber = 0;
    private int BLOCK_SIZE = 24;
    private int SCREEN_SIZE = N_BLOCKS*BLOCK_SIZE;
    private final short levelData[] = {
            19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
            21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            21, 0, 0, 0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20,
            17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20,
            25, 16, 16, 16, 24, 24, 28, 0, 25, 24, 24, 16, 20, 0, 21,
            1, 17, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 20, 0, 21,
            1, 17, 16, 16, 18, 18, 22, 0, 19, 18, 18, 16, 20, 0, 21,
            1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
            1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
            1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21,
            1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 21,
            1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
            9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 25, 24, 24, 24, 28
    };
    private Dimension d;


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

        screenData = new short[N_BLOCKS * N_BLOCKS];
        mazeColor = new Color(5, 100, 5);
        d = new Dimension(width, height);
        SCREEN_SIZE = d.height;
        BLOCK_SIZE = SCREEN_SIZE/N_BLOCKS;
    }

    @Override
    protected void updateGame()
    {
        if (pacMan.hasHeadCollisionWithWall() ||pacMan.isPacManCharOut(width, height))
            gameOver = true;

        int[] result = seedGenerator.checkSeedCollision(pacMan.getPacManChar().getX(), pacMan.getPacManChar().getY(), listOfIndex);
        if (result[1] == 1) {
            seedGenerator.changeSeedPosition(result[0]);
            pacMan.addPoint();
            eatenSeeds++;
            listOfIndex.remove(Integer.valueOf(result[0]));
        }
    }

    @Override
    protected boolean isGameWin()
    {
        return eatenSeeds == seedNumber;
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
    protected void initialGame()
    {
        pacMan.initial();
        initLevel();
        gameOver = false;
        gameStop = false;
        int i = 0;
        int x, y;

        for (y = yForShowDetail; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = xForShowDetail; x < SCREEN_SIZE; x += BLOCK_SIZE) {

//                g2d.setColor(mazeColor);
//                g2d.setStroke(new BasicStroke(2));

                if ((screenData[i] & 1) != 0) {
//                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) {
//                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) {
//                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
//                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) {
//                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
//                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0)
                {
                    seedNumber ++;
                    seedGenerator.initial(x, y, i);
                    seedGenerator.increaseSeedNumber();
                    listOfIndex.add(i);
                }

                i++;
            }
        }
//        packManLevel = LevelHandler.<PackManLevel>getLevelHandler().getCurrentLevel();
//        setDelay(packManLevel.getDelay());
    }

    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++)
        {
            screenData[i] = levelData[i];
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        doDrawing(g);
    }


    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0/*yForShowDetail*/; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0/*xForShowDetail*/; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(mazeColor);
                g2d.setStroke(new BasicStroke(2));

                if ((screenData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0)
                {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                i++;
            }
        }
    }

    @Override
    public void finish() {

    }
}
