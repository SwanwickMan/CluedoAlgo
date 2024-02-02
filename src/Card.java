import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Card {
    private CardType type;
    private CardValue value;

    public static final Set<CardValue> suspects = Set.of(CardValue.PEACOCK, CardValue.SCARLET, CardValue.MUSTARD, CardValue.GREEN, CardValue.PLUM, CardValue.ORCHID);
    public static final Set<CardValue> weapons = Set.of(CardValue.CANDLESTICK, CardValue.REVOLVER, CardValue.DAGGER, CardValue.WRENCH, CardValue.LEADPIPE, CardValue.ROPE);
    public static final Set<CardValue> rooms = Set.of(CardValue.HALL, CardValue.KITCHEN, CardValue.BALLROOM, CardValue.DININGROOM, CardValue.STUDY, CardValue.CONSERVATORY, CardValue.LIBRARY, CardValue.LOUNGE, CardValue.BILLIARDROOM);

    public Card(CardValue value){
        this.value = value;
        if (suspects.contains(value)){this.type = CardType.SUSPECT;}
        else if (rooms.contains(value)){this.type = CardType.ROOM;}
        else if (weapons.contains(value)){this.type = CardType.WEAPON;}
        else{System.exit(69);}
    }

    public CardType getType(){
        return type;
    }

    public CardValue getValue(){
        return value;
    }

    public String toString(){
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        return this.value.equals(((Card)o).value);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.value);
    }
}
