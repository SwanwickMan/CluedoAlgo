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

    public Player(String name){
        this.name = setName(name);
        this.active = true;
        this.notHave = new HashSet<Card>();
        this.doesHave = new HashSet<Card>();
        this.guessList = new ArrayList<HashSet<Card>>();
    }

    // checks if players is marked as user
    private String setName(String name){
        if (name.startsWith("*")){
            this.setAsUser();
            return name.substring(1);
        }
        else {return name;}

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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        return this.name == ((Player)o).name;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.name);
    }

    public boolean IsUser(){return isUser;}
    public void setAsUser(){this.isUser = true;}
}
