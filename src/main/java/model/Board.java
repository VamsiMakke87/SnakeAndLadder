package model;

import model.config.GameConfig;
import model.dice.Dice;
import model.exception.InvalidDiceTypeException;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board {

    private static GameConfig gameConfig;


    private static final Map<Integer, Obstacle> obstacleMap = new HashMap<>();
    ;
    private static Board instance;

    private static Random random = new Random();

    private Board(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        obstacleMap.put(0, null);
    }

    public static synchronized void intialize(GameConfig gameConfig) {
        if (instance == null) {
            instance = new Board(gameConfig);
            generateLadders();
            generateSnakes();
        }

    }

    private static void generateLadders() {
        int numberOfLadders = random.nextInt(6) + 1; // randomly generating n ladders- maximum 6 ladders

        for (int i = 0; i < numberOfLadders; i++) {
            int start = 0;
            while (obstacleMap.containsKey(start)) {
                start = random.nextInt(gameConfig.getBoardSize());
            }
            int end = random.nextInt(start + 1, gameConfig.getBoardSize());

            obstacleMap.put(start, new Obstacle(start, end, ObstacleType.LADDER));
        }
    }

    private static void generateSnakes() {
        int numberOfSnakes = random.nextInt(6) + 1; // randomly generating n snakes- maximum 6 snakes
        for (int i = 0; i < numberOfSnakes; i++) {
            int start = 0;
            while (obstacleMap.containsKey(start)) {
                start = random.nextInt(gameConfig.getBoardSize());
            }
            int end = random.nextInt(1, start);

            obstacleMap.put(start, new Obstacle(start, end, ObstacleType.SNAKE));
        }
    }

    public static Board getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Initilaize the board first");
        }
        return instance;
    }

    public void rollDice(Player player) throws InvalidDiceTypeException {
        List<Dice> diceList = gameConfig.getDices();
        int numberOfStepsToMove = 0;
        for (int i = 0; i < diceList.size(); i++) {
            int diceFace = diceList.get(i).roll();
            System.out.println("Dice " + (i + 1) + ":" + diceFace);
            numberOfStepsToMove += diceFace;
        }

        System.out.println("Number of steps to move:" + numberOfStepsToMove);

        moveSteps(player, numberOfStepsToMove);

    }

    private void moveSteps(Player player, int numberOfStepsToMove) {

        int currPosition = player.getPosition();
        int nextPosition = currPosition + numberOfStepsToMove;
        if (nextPosition > gameConfig.getBoardSize()) {
            nextPosition = currPosition;
            System.out.println("Can only move " + (gameConfig.getBoardSize() - currPosition) + " places");
        } else if (nextPosition < gameConfig.getBoardSize()) {
            if (obstacleMap.containsKey(nextPosition)) {
                Obstacle obstacle = obstacleMap.get(nextPosition);
                if (obstacle.getObstacleType() == ObstacleType.LADDER) {
                    System.out.println(player.getName() + " found a ladder!");
                } else {
                    System.out.println(player.getName() + " bitten by a snake");
                }
                nextPosition = obstacle.getEnd();
            }
        }

        player.setPosition(nextPosition);

    }

}
