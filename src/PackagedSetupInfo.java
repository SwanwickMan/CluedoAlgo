import java.util.Arrays;

public class PackagedSetupInfo {
    private final Player[] players;
    private final Player user;
    private final Card[] startingCards;
    private final int noOfPlayers;

    public PackagedSetupInfo(Player[] players, Card[] startingCards, int noOfPlayers){
        this.players = players;
        this.user = findUser();
        this.startingCards = startingCards;
        this.noOfPlayers = noOfPlayers;
        updateUserCards();

    }

    public boolean validate(){
        return validateNoOfPlayers() && containsNoDuplicates(startingCards)
                && containsNoDuplicates(players) && singleUserExists();
    }

    private boolean validateNoOfPlayers(){
        if (players.length == noOfPlayers){return true;}
        System.out.println("wrong number of players defined");
        return false;
    }

    private boolean containsNoDuplicates(Object[] arr){
        for (Object c1: arr){
            for (Object c2: arr){
                if (c1.equals(c2) && !(c1==c2)){
                    System.out.println("Error: duplicates: " + c1.toString() + " " + c2.toString());
                    return false; }
            }
        }
        return true;
    }
    private boolean singleUserExists(){
        int noOfUsers = 0;
        for (Player p: players){
            if (p.IsUser()){noOfUsers++;}
        }
        if (noOfUsers == 1){
            return true;
        }
        System.out.println("Error: program must have exactly one user defined with *Username");
        return false;
    }

    private void updateUserCards(){
        for (Player p: players){
            if(p.IsUser()){
                p.doesHave.addAll(Arrays.asList(startingCards));
            }
        }
    }

    private Player findUser(){
        for (Player p : players){
            if (p.IsUser()){return p;}
        }
        return new Player("NoUser");
    }

    public Player[] getPlayers() {
        return players;
    }
    public Player getUser() {
        return user;
    }

    public Card[] getStartingCards() {
        return startingCards;
    }
}
