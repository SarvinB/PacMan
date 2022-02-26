package pacman;

import GameObject.PacManChar;

public class PacMan
{
    private static final int PADDING = 3;
    public static int speed = 1;
    PacManChar pacManChar;
    private Callback callback;

    public PacMan(Callback callback)
    {
        this.callback = callback;
    }

    public void initial() {
        PacManChar pacManChar = new PacManChar("/head.png", null);
        this.pacManChar = pacManChar;
        pacManChar.setX(PacManGame.START_X + 10);
        pacManChar.setY(PacManGame.START_Y);
        pacManChar.setDirection(PacManChar.Direction.UP);
        this.pacManChar = pacManChar;
        this.callback.addPacMan(pacManChar);
    }



    // check collision with wall

    public boolean isPacManCharOut(int width, int height) {
        if (pacManChar.getX() < 0) return true;
        if (pacManChar.getY() < 0) return true;
        if (pacManChar.getX() + pacManChar.getWidth() > width) return true;
        if (pacManChar.getY() + pacManChar.getHeight() > height) return true;
        return false;
    }

    public void clear() {

    }

    interface Callback {
        void addPacMan(PacManChar pacManChar);
    }


}

