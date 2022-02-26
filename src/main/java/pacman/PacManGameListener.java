package pacman;
import gameEngine.GameListener;

public class PacManGameListener implements GameListener {

    private PacMan pacMan;
    private Stopper stopper;

    public PacManGameListener(PacMan pacMan, Stopper stopper) {
        this.pacMan = pacMan;
        this.stopper = stopper;
    }

    @Override
    public void onUpButtonClicked()
    {

    }

    @Override
    public void onDownButtonClicked() {

    }

    @Override
    public void onRightButtonClicked() {

    }

    @Override
    public void onLeftButtonClicked() {

    }

    @Override
    public void onSpaceButtonClicked() {

    }

    @Override
    public void onRightMouseButtonClicked(int x, int y) {

    }

    @Override
    public void onLeftMouseButtonClicked(int x, int y) {

    }

    public interface Stopper {
        void stop(boolean var1);

        boolean isStop();
    }
}
