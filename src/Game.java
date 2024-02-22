import java.util.Scanner;

public class Game {
    public Player[] players;
    private int turnIndex;

    public UserInterface gameUI;


    public Game(){
        int noOfPlayers = getNumberOfPlayer();
        PackagedSetupInfo setupInfo = new GameSetup(noOfPlayers).collectData();
        this.players = setupInfo.getPlayers();
        this.turnIndex = 0;
        this.gameUI = new UserInterface(this, players);
    }

    private int getNumberOfPlayer(){
        Scanner myObj = new Scanner(System.in);
        System.out.print("Number of players\n >>>");
        String number = myObj.nextLine();

        return Integer.parseInt(number);
    }


    public int getNextTurn(){
        boolean activePlayerExists = false;
        for(Player p : players){if(p.active){activePlayerExists = true;}}

        while (activePlayerExists){
            turnIndex ++;
            if (turnIndex >= players.length){
                turnIndex = 0;
            }
            if (players[turnIndex].active){
                return turnIndex;
            }
        }

        return -1;

    }
}
