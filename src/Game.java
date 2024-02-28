import java.util.Scanner;

public class Game {
    public Player[] players;
    private int turnIndex;
    private int noOfPlayers;

    public UserInterface gameUI;


    public Game(){
        this.noOfPlayers = getNumberOfPlayer();
        PackagedSetupInfo setupInfo = new GameSetup(noOfPlayers).collectData();
        this.players = setupInfo.getPlayers();
        this.turnIndex = 0;
        this.gameUI = new UserInterface(this, players);
    }

    public Game(PackagedSetupInfo setupInfo){
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


    public Player getNextPlayer(){
        boolean activePlayerExists = false;
        for(Player p : players){if(p.active){activePlayerExists = true;}}

        while (activePlayerExists){
            turnIndex ++;
            if (turnIndex >= players.length){
                turnIndex = 0;
            }
            if (players[turnIndex].active){
                return players[turnIndex];
            }
        }

        throw new RuntimeException("No players Left");

    }
}
