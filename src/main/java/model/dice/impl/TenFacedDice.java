package model.dice.impl;

import model.dice.Dice;

import java.util.Random;

public class TenFacedDice implements Dice {

    private final int faces = 10;
    private Random random = new Random();


    @Override
    public int roll() {
        return random.nextInt(faces) + 1;
    }

    @Override
    public int getFaces() {
        return this.faces;
    }
}
