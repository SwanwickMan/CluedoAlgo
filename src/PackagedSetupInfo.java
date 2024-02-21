public class PackagedSetupInfo {
    private Player[] players;
    private Card[] startingCards;

    public PackagedSetupInfo(Player[] players, Card[] startingCards){
        this.players = players;
        this.startingCards = startingCards;

    }

    public boolean validate(){
        if (players.length >= 3 &&)
        return true;
    }

    private boolean containsDuplicates(Card[] arr){
        boolean duplicate = false;
        for (Card c: arr){} // fix later
    }

    public Player[] getPlayers() {
        return  players;
    }

    public Card[] getStartingCards() {
        return startingCards;
    }
}
