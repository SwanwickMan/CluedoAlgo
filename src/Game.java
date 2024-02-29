import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Game {
    public Player[] players;
    public Player user;
    public Player currentPlayer;
    public Player currentPlayerAsked;
    private int noOfPlayers;
    public GameState gameState;
    public UserInterface gameUI;


    public Game(){
        this.noOfPlayers = getNumberOfPlayer();
        PackagedSetupInfo setupInfo = new GameSetup(noOfPlayers).collectData();
        this.players = setupInfo.getPlayers();
        this.user = setupInfo.getUser();
        this.currentPlayer = players[0];
        this.gameState = GameState.doesPlayerGuess;
        this.gameUI = new UserInterface(this, players);
    }

    public Game(PackagedSetupInfo setupInfo){
        this.players = setupInfo.getPlayers();
        this.currentPlayer = players[0];
        this.gameState = GameState.doesPlayerGuess;
        this.gameUI = new UserInterface(this, players);
    }

    private int getNumberOfPlayer(){
        Scanner myObj = new Scanner(System.in);
        System.out.print("Number of players\n >>>");
        String number = myObj.nextLine();

        return Integer.parseInt(number);
    }


    public Player getNextPlayerTurn(){
        boolean activePlayers = false;
        for(Player p : players){if (p.active) { activePlayers = true; break; }}
        if (!activePlayers) { throw new RuntimeException("No active players left"); }


        boolean afterCurrentPlayer = false;
        for (Player p: players){
            if (afterCurrentPlayer && p.active) {return p;}
            if (p.equals(currentPlayer)){afterCurrentPlayer = true;}
        }
        for (Player p: players){
            if (p.active) {return p;}
            if (p.equals(currentPlayer)){break;}
        }
        throw new RuntimeException("Single player Left");
    }
    public Player getNextPlayer(Player player){
        boolean afterCurrentPlayer = false;
        for (Player p: players){
            if (afterCurrentPlayer) {return p;}
            if (p.equals(currentPlayer)){afterCurrentPlayer = true;}
        }
        for (Player p: players){
            if (!p.equals(currentPlayer)){return p;}
        }
        throw new RuntimeException("player not found");
    }

    public void takeTurn(Player player){
        if (player.isUser()) { userTakeTurn(); }
        else { nonUserTakeTurn(); }
    }

    public void playerTakesGuess(){
        gameState = GameState.playerGuesses;
        currentPlayerAsked = getNextPlayer(currentPlayer);
        gameUI.updateButtonsToPlayerGuesses();
    }

    public void showOtherPlayerCards(){
        if (currentPlayerAsked.isUser()){ return; } // do nothing if user is showing their cards
        else if (currentPlayer.isUser()){
            // when user is shown card set card to not guilty and add to other player hasList
            Card shownCard = gameUI.getCardShownToUser();
            gameUI.setCardColumnNotGuilty(shownCard);
            currentPlayerAsked.addHasCard(shownCard);
        }
        else {
            // when non-users shows cards add cards to player showings guessList
            HashSet<Card> shownCards = gameUI.getCards();
            currentPlayerAsked.guessList.add(shownCards);
        }
    }

    public void userTakeTurn(){

    }

    public void nonUserTakeTurn(){

    }


}
