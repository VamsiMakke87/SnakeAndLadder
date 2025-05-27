package model;

public class Player {

    private String name;

    private int positon;

    public Player(String name){
        this.name=name;
        this.positon=0;
    }

    public String getName() {
        return name;
    }

    public int getPositon() {
        return positon;
    }

    public void setPositon(int positon) {
        this.positon = positon;
    }
}
