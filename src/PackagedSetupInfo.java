public class PackagedSetupInfo {
    private Player[] players;
    private Card[] startingCards;

    public PackagedSetupInfo(Player[] players, Card[] startingCards){
        this.players = players;
        this.startingCards = startingCards;

    }

    public boolean validate(){
        if (players.length >= 3 && !containsDuplicates(startingCards)) {
            return true;
        }
        else { return false; }
    }

    private boolean containsDuplicates(Card[] arr){
        for (Card c1: arr){
            for (Card c2: arr){
                if (c1.equals(c2)){ return true; }
            }
        }
        return false;
    }

    public Player[] getPlayers() {
        return  players;
    }

    public Card[] getStartingCards() {
        return startingCards;
    }
}
