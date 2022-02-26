package pacman;

import gameEngine.level.Level;

public class PackManLevel extends Level
{
    private int totalSeeds;
    private long delay;

    public PackManLevel(int totalSeeds, long delay) {
        this.totalSeeds = totalSeeds;
        this.delay = delay;
    }

    public int getTotalSeeds() {
        return totalSeeds;
    }

    public long getDelay() {
        return delay;
    }
}
