import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class Game {
    public Player[] players;
    private int turnIndex;

    private UserInterface gameUI;

    public Game(){
        Scanner myObj = new Scanner(System.in);
        System.out.print("Unique Player Names Separated by commas with no spaces \n >>>");

        // get all players
        String names = myObj.nextLine();
        names = names.replaceAll("\\s+","");
        // convert String[] array to Player[] array
        this.players = Arrays.stream(names.split(","))
                .map(Player::new)
                .toArray(Player[]::new);

        // set turn index
        turnIndex = 0;

        this.gameUI = new UserInterface(this, players);
    }

    public int getNextTurn(){
        while (true){
            turnIndex ++;
            if (turnIndex >= players.length){
                turnIndex = 0;
            }
            if (players[turnIndex].active){
                return turnIndex;
            }
        }

    }
}
