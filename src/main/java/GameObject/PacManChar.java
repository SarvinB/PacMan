package GameObject;

import gameEngine.gameObject.AutoMoverSprite;
import pacman.PacMan;
import pacman.PacManGame;

public class PacManChar extends AutoMoverSprite
{
    public enum Direction {UP, DOWN, LEFT, RIGHT}
    private Direction direction;

    public PacManChar(String imagePath) {
        super(imagePath);
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
    }

}
