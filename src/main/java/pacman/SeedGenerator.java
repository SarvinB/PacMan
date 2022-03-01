package pacman;

import GameObject.Seed;
import pacman.other.Pair;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class SeedGenerator
{
    public static final int SEED_SIZE = 10;
    private Hashtable<Integer, Seed> seedList = new Hashtable<>();
    private SeedAdder seedAdder;
    private PositionChecker positionChecker;
    private Random random;
    private int boardWidth;
    private int boardHeight;
    private int seedNumber=0;

    public SeedGenerator(SeedAdder seedAdder, PositionChecker positionChecker, int boardWidth, int boardHeight) {
        this.seedAdder = seedAdder;
        this.positionChecker = positionChecker;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        random = new Random();
    }

    public void initial(int x, int y, int i)
    {
        Seed seed = new Seed();
        seed.setX(x);
        seed.setY(y);
        seed.setWidth(SEED_SIZE);
        seed.setHeight(SEED_SIZE);
        seedList.put(i, seed);
        this.seedAdder.addSeed(seed);
    }

    public void increaseSeedNumber()
    {
        seedNumber++;
    }

    public int[] checkSeedCollision(int x, int y, ArrayList<Integer> listOfIndex) {

        int[] r = new int[2];
        for(int i:listOfIndex)
        {
            boolean b = y > seedList.get(i).getY() - 1 &&
                    y < seedList.get(i).getY() + SEED_SIZE &&
                    x > seedList.get(i).getX() - PacManGame.PACK_MAN_SIZE &&
                    x < seedList.get(i).getX() + SEED_SIZE;
            if(b)
            {
                r[0] = i;
                r[1] = 1;
            }
        }

        return r;
    }

    public void changeSeedPosition(int i) {
        int x = random.nextInt(boardWidth - SEED_SIZE);
        int y = random.nextInt(boardHeight - SEED_SIZE);

        while (!isPositionEmpty(x, y)) {
            x = random.nextInt(boardWidth - SEED_SIZE);
            y = random.nextInt(boardHeight - SEED_SIZE);
        }
        seedList.get(i).setX(0);
        seedList.get(i).setY(0);
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
