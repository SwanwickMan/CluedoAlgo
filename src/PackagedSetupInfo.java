public class PackagedSetupInfo {
    private final Player[] players;
    private final Card[] startingCards;
    private final int noOfPlayers;

    public PackagedSetupInfo(Player[] players, Card[] startingCards, int noOfPlayers){
        this.players = players;
        this.startingCards = startingCards;
        this.noOfPlayers = noOfPlayers;

    }

    public boolean validate(){
        System.out.println(noOfPlayers);System.out.println(!containsDuplicates());System.out.println(singleUserExists());
        return players.length == noOfPlayers && !containsDuplicates() && singleUserExists();
    }

    private boolean containsDuplicates(){
        for (Card c1: startingCards){
            for (Card c2: startingCards){
                if (c1.equals(c2) && !(c1==c2)){ return true; }
            }
        }
        return false;
    }
    private boolean singleUserExists(){
        int noOfUsers = 0;
        for (Player p: players){
            if (p.IsUser()){noOfUsers++;}
        }
        return noOfUsers == 1;
    }

    public Player[] getPlayers() {
        return  players;
    }

    public Card[] getStartingCards() {
        return startingCards;
    }
}
