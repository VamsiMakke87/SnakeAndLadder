import model.config.GameConfig;
import model.dice.DiceType;
import model.obstacle.Obstacle;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter number of players:");
            int numberOfPlayers = sc.nextInt();
            System.out.println("Enter board size:");
            int boardSize = sc.nextInt();
            DiceType diceType;

            while (true) {
                try {
                    System.out.println("Enter dice type: (SIXFACED, EIGHTFACED, TENFACED");
                    String diceTypeInput = sc.next().toUpperCase();
                    diceType = DiceType.valueOf(diceTypeInput);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid dice type!");
                }
            }

            int numberOfDice;
            while (true) {
                System.out.println("Enter number of dice:");
                numberOfDice = sc.nextInt();
                if (numberOfDice > boardSize) {
                    System.out.println("Number of dice cannot be more than board size(" + boardSize + ")");
                } else {
                    break;
                }
            }
            GameConfig gameConfig = new GameConfig(numberOfDice, boardSize, diceType);






        } catch (Exception e) {
            System.out.println("Unknown error occured! Please restart the app");
        }

    }
}
