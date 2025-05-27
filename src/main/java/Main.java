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
            Deque<Player> playerDeque=new ArrayDeque<>();
            System.out.println("Enter number of players:");
            int numberOfPlayers = Integer.parseInt(sc.nextLine());
            for (int i=1;i<=numberOfPlayers;i++){
                System.out.println("Enter player "+i+" name:");
                String playerName= sc.nextLine();
                playerDeque.add(new Player(playerName));
            }
            System.out.println("Enter board size:");
            int boardSize = Integer.parseInt(sc.nextLine());
            DiceType diceType;

            System.out.println(playerDeque);

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
            Board board=Board.getInstance();
            List<Player> winners=new ArrayList<>();


            while (true){

                Player currPlayer= playerDeque.removeFirst();
                int previousPosition=currPlayer.getPositon();
                System.out.println(currPlayer.getName()+"'s turn");
                board.rollDice(currPlayer);
                if(currPlayer.getPositon()==boardSize){
                    System.out.println("______________________________________________");
                    System.out.println(currPlayer.getName()+" reached destination");
                    System.out.println("______________________________________________");
                    winners.add(currPlayer);
                    if(playerDeque.size()==1)break;
                }else{
                    System.out.println(currPlayer.getName()+" move from "+previousPosition+" to "+currPlayer.getPositon());
                    playerDeque.addLast(currPlayer);
                }
            }

            for(int i=0;i<winners.size();i++){
                System.out.println("Winner "+(i+1)+":"+winners.get(i).getName());
            }
            System.out.println(playerDeque.removeFirst().getName()+" lost");
        } catch (Exception e) {
            System.out.println("Unknown error occured! Please restart the app");
        }

    }
}
