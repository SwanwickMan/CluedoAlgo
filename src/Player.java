import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Player {
    public String name;
    public boolean active;
    private boolean isUser = false;
    public Set<Card> notHave;
    public Set<Card> doesHave;
    public ArrayList<HashSet<Card>> guessList;
    private final int maxCardsPerPlayer;

    public Player(String name, int maxCardsPerPlayer){
        this.name = setName(name);
        this.active = true;
        this.notHave = new HashSet<>();
        this.doesHave = new HashSet<>();
        this.guessList = new ArrayList<>();
        this.maxCardsPerPlayer = maxCardsPerPlayer;
    }

    public Player(Player player){
        this.name = player.name;
        this.active = player.active;
        this.notHave = new HashSet<>(player.notHave);
        this.doesHave = new HashSet<>(player.doesHave);
        this.guessList = new ArrayList<>(player.guessList);
        this.maxCardsPerPlayer = player.maxCardsPerPlayer;
    }

    // checks if players is marked as user
    private String setName(String name){
        if (name.startsWith("*")){
            this.setAsUser();
            return name.substring(1);
        }
        else {return name;}

    }

    public void showNothing(Card suspect, Card weapon, Card room){
        notHave.add(suspect);
        notHave.add(weapon);
        notHave.add(room);

        EvaluateSelf();
    }

    public void showOther(Card suspect, Card weapon, Card room){
        // add guess to list
        guessList.add(new HashSet<>(Arrays.asList(suspect, weapon, room)));

        EvaluateSelf();
    }

    public void showUser(Card cardShown){
        doesHave.add(cardShown);
        EvaluateSelf();
    }

    public void EvaluateSelf(){
        if (doesHave.size() == maxCardsPerPlayer) { // update notHave when all players cards are discovered
            Set<Card> remainingCards = new HashSet<>(Card.allCards);
            remainingCards.removeAll(doesHave);
            notHave.addAll(remainingCards);
        }
        evaluateGuessList();
    }

    private void evaluateGuessList(){
        for (Set<Card> guess : guessList){
            Set<Card> difference = new HashSet<>(guess);
            difference.removeAll(notHave);
            if (difference.size() == 1){ // if only 1 card in guess not in notHave then add card to doesHave
                Card inferredCard = difference.iterator().next();
                doesHave.add(inferredCard);
            }
        }
    }

    public void addHasCard(Card card){
        if (notHave.contains(card)) {throw new RuntimeException("Contradiction Error: Card" + card + "already in notHave Set of " + this);}
        this.doesHave.add(card);
    }
    public void addHasCard(HashSet<Card> cards){
        for (Card c: cards){addHasCard(c);}
    }
    public void addNotHasCard(Card card){
        if (doesHave.contains(card)) {throw new RuntimeException("Contradiction Error: Card" + card + "already in notHave Set of " + this);}
        this.notHave.add(card);
    }
    public void addNotHasCard(HashSet<Card> cards){
        for (Card c: cards){addNotHasCard(c);}
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        return Objects.equals(this.name, ((Player) o).name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.name);
    }

    public boolean isUser(){return isUser;}
    public void setAsUser(){this.isUser = true;}
}
