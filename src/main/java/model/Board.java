package model;

import model.config.GameConfig;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {

    private static GameConfig gameConfig;


    private static final Map<Integer, Obstacle> obstacleMap= new HashMap<>();;
    private static Board instance;

    private static Random random=new Random();

    private Board(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        obstacleMap.put(0,null);
    }

    public static synchronized void intialize(GameConfig gameConfig){
        if(instance==null){
            instance=new Board(gameConfig);
            generateLadders();
            generateSnakes();
        }

    }

    private static void generateLadders(){
        int numberOfLadders= random.nextInt(6)+1; // randomly generating n ladders- maximum 6 ladders

        for(int i=0;i<numberOfLadders;i++){
            int start=0;
            while(obstacleMap.containsKey(start)){
                start=random.nextInt(gameConfig.getBoardSize());
            }
            int end=random.nextInt(start+1,gameConfig.getBoardSize());

            obstacleMap.put(start,new Obstacle(start,end, ObstacleType.LADDER));
        }
    }

    private static void generateSnakes(){
        int numberOfSnakes= random.nextInt(6)+1; // randomly generating n snakes- maximum 6 snakes
        for(int i=0;i<numberOfSnakes;i++){
            int start=0;
            while(obstacleMap.containsKey(start)){
                start=random.nextInt(gameConfig.getBoardSize());
            }
            int end=random.nextInt(1,start);

            obstacleMap.put(start,new Obstacle(start,end, ObstacleType.SNAKE));
        }
    }

    public static Board getInstance(){
        if(instance==null){
            throw new IllegalStateException("Initilaize the board first");
        }
        return instance;
    }

    

}
