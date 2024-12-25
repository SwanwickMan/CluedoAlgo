import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    public final int maxCardsPerPlayer;
    public Player[] players;
    public Player user;
    public Player currentPlayer;
    public Player currentPlayerAsked;
    public GameState gameState;
    public UserInterface gameUI;
    public Set<Card> innocent = new HashSet<>();


    public Game(){
        int noOfPlayers = getNumberOfPlayer();
        PackagedSetupInfo setupInfo = new GameSetup(noOfPlayers).collectData();
        this.players = setupInfo.getPlayers();
        this.user = setupInfo.getUser();
        this.currentPlayer = players[players.length-1]; // set to last cause of reasons
        this.gameState = GameState.doesPlayerGuess;
        this.gameUI = new UserInterface(this, players);
        this.innocent.addAll(user.doesHave);
        this.maxCardsPerPlayer = (int)Math.ceil(18.0/noOfPlayers);
    }

    public Game(PackagedSetupInfo setupInfo){
        this.players = setupInfo.getPlayers();
        this.user = setupInfo.getUser();
        this.currentPlayer = players[0];
        this.gameState = GameState.doesPlayerGuess;
        this.gameUI = new UserInterface(this, players);
        this.innocent.addAll(user.doesHave);
        this.maxCardsPerPlayer = (int)Math.ceil(18.0/players.length);
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
        for (int i=0; i<players.length; i++){
            if (players[i] == player) { return players[(i+1) % players.length]; }
        }
        throw new RuntimeException("player not found");
    }

    // sets game state and updates UI
    private void setGameState(GameState gameState){
        this.gameState = gameState;
        if (gameState == GameState.doesPlayerGuess) { gameUI.updateButtonsToDoesPlayerGuess();}
        else if (gameState == GameState.playerGuesses) {
            gameUI.updateButtonsToPlayerGuesses();
            gameUI.debugUpdateAllPlayerColumns();
        }

    }

    public void playerTakesGuess(){
        currentPlayerAsked = getNextPlayer(currentPlayer);
        setGameState(GameState.playerGuesses);
    }

    public void playerDoesNotTakeTurn(){
        currentPlayer = getNextPlayerTurn();
        setGameState(GameState.doesPlayerGuess);
    }

    public void showOtherPlayerCards(){
        // decide how to deal with card being shown
        if (!currentPlayerAsked.isUser()){ // do nothing if user is showing their cards
            if (currentPlayer.isUser()){ // when user is shown card set card to not guilty and add to other player hasList
                Card shownCard = gameUI.getCardShownToUser();
                gameUI.setCardColumnNotGuilty(shownCard);
                userShownCard(currentPlayerAsked, shownCard);
            }
            else { // when non-users shows cards add cards to player showings guessList
                HashSet<Card> shownCards = gameUI.getCards();
                currentPlayerAsked.guessList.add(shownCards);
            }
        }
        currentPlayer = getNextPlayerTurn();
        setGameState(GameState.doesPlayerGuess);
    }



    public void playerDoesNotShowCards(){
        // add cards to not has list
        HashSet<Card> cards = gameUI.getCards();
        currentPlayerAsked.addNotHasCard(cards);

        // skip to ask next player;
        currentPlayerAsked = getNextPlayer(currentPlayerAsked);
        System.out.println(currentPlayerAsked.name +"|"+currentPlayer.name);
        System.out.println(getNextPlayer(currentPlayerAsked).name);

        // return after loop reaches start
        if (currentPlayerAsked == currentPlayer){
            setGameState(GameState.doesPlayerGuess);
            currentPlayer = getNextPlayerTurn();
        }
    }

    private void userShownCard(Player other, Card c){
        for (Player p : players){
            if (p == other) { p.showUser(c); }
            else { p.addNotHasCard(c); }
        }
    }
}
