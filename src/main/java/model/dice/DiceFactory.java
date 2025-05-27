package model.dice;

import model.dice.impl.EightFacedDice;
import model.dice.impl.SixFacedDice;
import model.dice.impl.TenFacedDice;
import model.exception.InvalidDiceTypeException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DiceFactory {

    private Map<DiceType, Supplier<Dice>> diceFactory = new HashMap<>();

    public DiceFactory(){
        diceFactory.put(DiceType.SIXFACED,()-> new SixFacedDice());
        diceFactory.put(DiceType.EIGHTFACED,()-> new EightFacedDice());
        diceFactory.put(DiceType.TENFACED,()-> new TenFacedDice());
    }


    public Dice getDice(DiceType diceType) throws InvalidDiceTypeException {
        if (!diceFactory.containsKey(diceType)) {
            throw new InvalidDiceTypeException("Invalid Dice Type");
        }
        return diceFactory.get(diceType).get();
    }

    public void addDice(DiceType diceType, Supplier<Dice> diceSupplier) {
        diceFactory.put(diceType, diceSupplier);
    }

}
