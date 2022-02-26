package pacman;

import GameObject.PacManChar;
import pacman.other.Pair;

public class PacMan
{
    private static final int PADDING = 3;
    public static int speed = 1;
    PacManChar pacManChar;
    private Callback callback;
    private int points = 0;

    public PacMan(Callback callback)
    {
        this.callback = callback;
    }

    public void initial() {
        PacManChar pacManChar = new PacManChar("/pacman.png");
        this.pacManChar = pacManChar;
        pacManChar.setX(PacManGame.START_X + 10);
        pacManChar.setY(PacManGame.START_Y);
        pacManChar.setDirection(PacManChar.Direction.UP);
        this.pacManChar = pacManChar;
        this.callback.addPacMan(pacManChar);
    }

    public Pair<Integer, Integer> pacManPositions() {
        return new Pair<>(pacManChar.getX(), pacManChar.getY());
    }

    public PacManChar getPacManChar() {
        return pacManChar;
    }


    public boolean hasHeadCollisionWithWall() {
        return false;
    }

    public boolean isPacManCharOut(int width, int height) {
        if (pacManChar.getX() < 0) return true;
        if (pacManChar.getY() < 0) return true;
        if (pacManChar.getX() + pacManChar.getWidth() > width) return true;
        if (pacManChar.getY() + pacManChar.getHeight() > height) return true;
        return false;
    }

    public void addPoint()
    {
        points += 100;
    }

    public void clear() {

    }

    interface Callback {
        void addPacMan(PacManChar pacManChar);
    }


}

