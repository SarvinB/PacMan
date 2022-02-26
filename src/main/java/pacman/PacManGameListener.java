package pacman;
import GameObject.PacManChar;
import gameEngine.GameListener;

public class PacManGameListener implements GameListener {

    private PacMan pacMan;
    private Stopper stopper;

    public PacManGameListener(PacMan pacMan, Stopper stopper) {
        this.pacMan = pacMan;
        this.stopper = stopper;
    }

    @Override
    public void onUpButtonClicked() {
        if (pacMan.getPacManChar().getDirection() == PacManChar.Direction.DOWN) return;
        pacMan.getPacManChar().setAngle(0);
        pacMan.getPacManChar().setDirection(PacManChar.Direction.UP);
    }

    @Override
    public void onDownButtonClicked() {
        if (pacMan.getPacManChar().getDirection() == PacManChar.Direction.UP) return;
        pacMan.getPacManChar().setAngle(2);
        pacMan.getPacManChar().setDirection(PacManChar.Direction.DOWN);
    }

    @Override
    public void onRightButtonClicked() {
        if (pacMan.getPacManChar().getDirection() == PacManChar.Direction.LEFT) return;
        pacMan.getPacManChar().setAngle(1);
        pacMan.getPacManChar().setDirection(PacManChar.Direction.RIGHT);
    }

    @Override
    public void onLeftButtonClicked() {
        if (pacMan.getPacManChar().getDirection() == PacManChar.Direction.RIGHT) return;
        pacMan.getPacManChar().setAngle(3);
        pacMan.getPacManChar().setDirection(PacManChar.Direction.LEFT);
    }

    @Override
    public void onSpaceButtonClicked() {
        stopper.stop(!stopper.isStop());
    }

    @Override
    public void onRightMouseButtonClicked(int x, int y) {

    }

    @Override
    public void onLeftMouseButtonClicked(int x, int y) {

    }


    public interface Stopper {
        void stop(boolean b);

        boolean isStop();
    }
}
