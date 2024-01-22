import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Player {
    public String name;
    public boolean active;
    public Set<Card> notHave;
    public Set<Card> doesHave;
    public ArrayList<HashSet<Card>> guessList;

    public Player(String name){
        this.name = name;
        this.active = true;
        this.notHave = new HashSet<Card>();
        this.doesHave = new HashSet<Card>();
        this.guessList = new ArrayList<HashSet<Card>>();
    }

    public void passTurn(Card suspect, Card weapon, Card room){
        notHave.add(suspect);
        notHave.add(weapon);
        notHave.add(room);
    }

    public void showOther(Card suspect, Card weapon, Card room){
        // add guess to list
        guessList.add(new HashSet<Card>(Arrays.asList(suspect,weapon,room)));
    }

    public void showMe(Card cardShown){
        doesHave.add(cardShown);
    }

    public void EvaluateSelf(){

    }

    @Override
    public String toString() {
        return this.name;
    }
}
