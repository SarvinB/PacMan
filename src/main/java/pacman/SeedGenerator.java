package pacman;

import GameObject.Seed;
import pacman.other.Pair;

import java.util.List;
import java.util.Random;

public class SeedGenerator
{
    public static final int SEED_SIZE = 50;
    private Seed seed;
    private SeedAdder seedAdder;
    private PositionChecker positionChecker;
    private Random random;
    private int boardWidth;
    private int boardHeight;

    public SeedGenerator(SeedAdder seedAdder, PositionChecker positionChecker, int boardWidth, int boardHeight) {
        this.seedAdder = seedAdder;
        this.positionChecker = positionChecker;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        random = new Random();
    }

    public void initial() {
        seed = new Seed();
        seed.setX(100);
        seed.setY(100);
        seed.setWidth(SEED_SIZE);
        seed.setHeight(SEED_SIZE);
        this.seedAdder.addSeed(seed);
    }

    public boolean checkSeedCollision(int x, int y) {
        return y > seed.getY() - 1 &&
                y < seed.getY() + SEED_SIZE &&
                x > seed.getX() - PacManGame.PACK_MAN_SIZE &&
                x < seed.getX() + SEED_SIZE;
    }

    public void changeSeedPosition() {
        int x = random.nextInt(boardWidth - SEED_SIZE);
        int y = random.nextInt(boardHeight - SEED_SIZE);

        while (!isPositionEmpty(x, y)) {
            x = random.nextInt(boardWidth - SEED_SIZE);
            y = random.nextInt(boardHeight - SEED_SIZE);
        }

        seed.setX(x);
        seed.setY(y);
    }

    private boolean isPositionEmpty(int x, int y) {

            if (positionChecker.positions().getF() < x + 2 * SEED_SIZE && positionChecker.positions().getF() > x - SEED_SIZE)
                if (positionChecker.positions().getS() < y + 2 * SEED_SIZE && positionChecker.positions().getS() > y - SEED_SIZE)
                    return false;
        return true;
    }

    interface PositionChecker {
        Pair<Integer, Integer> positions();
    }

    interface SeedAdder {
        void addSeed(Seed seed);
    }
}
