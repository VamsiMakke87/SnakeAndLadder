package model;

import model.config.GameConfig;

public class Board {

    private GameConfig gameConfig;

    private static Board instance;

    private Board(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public static synchronized void intialize(GameConfig gameConfig){
        if(instance==null){
            instance=new Board(gameConfig);
        }

    }

    public static Board getInstance(){
        if(instance==null){
            throw new IllegalStateException("Initilaize the board first");
        }
        return instance;
    }



}
