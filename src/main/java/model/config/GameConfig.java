package model.config;

import model.dice.Dice;
import model.dice.DiceFactory;
import model.dice.DiceType;
import model.exception.InvalidDiceTypeException;

import java.util.ArrayList;
import java.util.List;

public class GameConfig {

    private final int numberOfDice;

    private final int boardSize;

    private List<Dice> dices;

    private final DiceType diceType;

    private final DiceFactory diceFactory;

    public GameConfig(int numberOfDice, int boardSize, DiceType diceType) throws InvalidDiceTypeException {
        this.numberOfDice = numberOfDice;
        this.boardSize = boardSize;
        this.diceType = diceType;
        this.diceFactory = new DiceFactory();

    }

    public List<Dice> getDices() throws InvalidDiceTypeException {

        if (this.dices == null) {
            this.dices = new ArrayList<>();
            for (int i = 0; i < this.numberOfDice; i++) {
                this.dices.add(diceFactory.getDice(this.diceType));
            }
        }

        return this.dices;
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }

    public DiceType getDiceType() {
        return diceType;
    }
}
