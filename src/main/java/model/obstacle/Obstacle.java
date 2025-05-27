package model.obstacle;

public class Obstacle {

    private final int start;
    private final int end;

    private final ObstacleType obstacleType;

    public Obstacle(int start, int end, ObstacleType obstacleType) {
        this.start = start;
        this.end = end;
        this.obstacleType = obstacleType;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public ObstacleType getObstacleType() {
        return obstacleType;
    }
}
