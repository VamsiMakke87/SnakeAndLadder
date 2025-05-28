import model.Board;
import model.Player;
import model.config.GameConfig;
import model.dice.DiceType;
import model.obstacle.Obstacle;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);
            Deque<Player> playerDeque = new ArrayDeque<>();
            System.out.println("Enter number of players:");
            int numberOfPlayers = 0;
            while (true) {
                numberOfPlayers = Integer.parseInt(sc.nextLine());
                if (numberOfPlayers < 1) {
                    System.out.println("Minimum 2 players required to start the game!");
                } else {
                    break;
                }
            }
            for (int i = 1; i <= numberOfPlayers; i++) {
                String playerName;
                while (true) {
                    System.out.println("Enter player " + i + " name:");
                    playerName = sc.nextLine();
                    if (playerName.trim().length() == 0) {
                        System.out.println("Player name has to be atleast one character!");
                    } else {
                        break;
                    }
                }
                playerDeque.add(new Player(playerName));
            }
            System.out.println("Enter board size:");
            int boardSize = Integer.parseInt(sc.nextLine());
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
                numberOfDice = Integer.parseInt(sc.next());
                if (numberOfDice > boardSize) {
                    System.out.println("Number of dice cannot be more than board size(" + boardSize + ")");
                } else {
                    break;
                }
            }
            GameConfig gameConfig = new GameConfig(numberOfDice, boardSize, diceType);
            Board.intialize(gameConfig);
            Board board = Board.getInstance();
            List<Player> winners = new ArrayList<>();


            while (true) {

                Player currPlayer = playerDeque.removeFirst();
                int previousPosition = currPlayer.getPosition();
                System.out.println(currPlayer.getName() + "'s turn");
                board.rollDice(currPlayer);
                System.out.println(currPlayer.getName() + " move from " + previousPosition + " to " + currPlayer.getPosition());
                if (currPlayer.getPosition() == boardSize) {
                    System.out.println("______________________________________________");
                    System.out.println(currPlayer.getName() + " reached destination");
                    System.out.println("______________________________________________");
                    winners.add(currPlayer);
                    if (playerDeque.size() == 1) break;
                } else {
                    playerDeque.addLast(currPlayer);
                }
            }

            for (int i = 0; i < winners.size(); i++) {
                System.out.println("Winner " + (i + 1) + ":" + winners.get(i).getName());
            }
            System.out.println(playerDeque.removeFirst().getName() + " lost");
        } catch (Exception e) {
            System.out.println("Unknown error occured! Please restart the app");
        }

    }
}
