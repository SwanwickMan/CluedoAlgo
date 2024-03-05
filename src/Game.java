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
        this.currentPlayer = players[players.length-1]; // set to last cause of reasons
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

    // sets game state and updates UI
    private void setGameState(GameState gameState){
        this.gameState = gameState;
        if (gameState == GameState.doesPlayerGuess) { gameUI.updateButtonsToDoesPlayerGuess();}
        else if (gameState == GameState.playerGuesses) { gameUI.updateButtonsToPlayerGuesses();}

    }

    public void playerTakesGuess(){
        currentPlayerAsked = getNextPlayer(currentPlayer);
        setGameState(GameState.playerGuesses);
    }

    public void showOtherPlayerCards(){
        // decide how to deal with card being shown
        if (currentPlayerAsked.isUser()){ // do nothing if user is showing their cards
            return;
        }
        else if (currentPlayer.isUser()){ // when user is shown card set card to not guilty and add to other player hasList
            Card shownCard = gameUI.getCardShownToUser();
            gameUI.setCardColumnNotGuilty(shownCard);
            currentPlayerAsked.addHasCard(shownCard);
        }
        else { // when non-users shows cards add cards to player showings guessList
            HashSet<Card> shownCards = gameUI.getCards();
            currentPlayerAsked.guessList.add(shownCards);
        }
        setGameState(GameState.doesPlayerGuess);
    }

    public void playerDoesNotTakeTurn(){
        currentPlayer = getNextPlayerTurn();
        setGameState(GameState.doesPlayerGuess);
    }

    public void playerDoesNotShowCards(){
        // add cards to not has list
        HashSet<Card> cards = gameUI.getCards();
        currentPlayerAsked.addNotHasCard(cards);

        // skip to ask next player;
        currentPlayerAsked = getNextPlayer(currentPlayerAsked);

        // return after loop reaches start
        if (currentPlayerAsked == currentPlayer){
            setGameState(GameState.doesPlayerGuess);
            // fix later add more deductive logic
        }
    }

    public void userTakeTurn(){

    }

    public void nonUserTakeTurn(){

    }


}
