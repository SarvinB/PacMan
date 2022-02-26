package GameObject;

import gameEngine.gameObject.AutoMoverSprite;
import pacman.PacMan;
import pacman.PacManGame;

public class PacManChar extends AutoMoverSprite
{
    public enum Direction {UP, DOWN, LEFT, RIGHT}
    private PacMan pacMan;
    private Direction direction;

    public PacManChar(String imagePath, PacMan pacMan) {
        super(imagePath);
        this.pacMan = pacMan;
        setHeight(PacManGame.PACK_MAN_SIZE);
        setWidth(PacManGame.PACK_MAN_SIZE);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        switch (direction) {
            case UP:
                setyStep(-1 * PacMan.speed);
                setxStep(0);
                break;
            case DOWN:
                setyStep(PacMan.speed);
                setxStep(0);
                break;
            case LEFT:
                setxStep(-1 * PacMan.speed);
                setyStep(0);
                break;
            case RIGHT:
                setxStep(PacMan.speed);
                setyStep(0);
                break;
        }
    }

    @Override
    public void update() {
        super.update();
//        if (parentBead != null) {
//            if (getDirection() == Direction.RIGHT || getDirection() == Direction.LEFT)
//                if (getX() == pacManC.getX())
//                    if (parentBead.getY() < getY()) setDirection(Direction.UP);
//                    else setDirection(Direction.DOWN);
//            if (getDirection() == Direction.UP || getDirection() == Direction.DOWN)
//                if (getY() == parentBead.getY())
//                    if (parentBead.getX() < getX()) setDirection(Direction.LEFT);
//                    else setDirection(Direction.RIGHT);
//        }

    }

}
