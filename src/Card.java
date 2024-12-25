import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Card {
    private final CardType type;
    private final CardValue value;


    public static final Set<CardValue> suspectTypes = Set.of(CardValue.PEACOCK, CardValue.SCARLET, CardValue.MUSTARD, CardValue.GREEN, CardValue.PLUM, CardValue.ORCHID);
    public static final Set<CardValue> weaponTypes = Set.of(CardValue.CANDLESTICK, CardValue.REVOLVER, CardValue.DAGGER, CardValue.WRENCH, CardValue.LEADPIPE, CardValue.ROPE);
    public static final Set<CardValue> roomTypes = Set.of(CardValue.HALL, CardValue.KITCHEN, CardValue.BALLROOM, CardValue.DININGROOM, CardValue.STUDY, CardValue.CONSERVATORY, CardValue.LIBRARY, CardValue.LOUNGE, CardValue.BILLIARDROOM);

    public static final Set<Card> suspects = createCardTypeSet(suspectTypes);
    public static final Set<Card> weapons = createCardTypeSet(weaponTypes);
    public static final Set<Card> rooms = createCardTypeSet(roomTypes);
    public static final Set<Card> allCards = getAllCards();



    public Card(CardValue value){
        this.value = value;
        if (suspectTypes.contains(value)){this.type = CardType.SUSPECT;}
        else if (weaponTypes.contains(value)){this.type = CardType.WEAPON;}
        else if (roomTypes.contains(value)){this.type = CardType.ROOM;}
        else{throw new RuntimeException("Bad card created");}
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

    private static Set<Card> createCardTypeSet(Set<CardValue> valueSet){
        Set<Card> CardSet = new HashSet<>();
        for (CardValue cv : valueSet){
            CardSet.add(new Card(cv));
        }
        return CardSet;
    }

    public static Card[] getArrayOfAllCards(){
        Set<Card> cardSet = new HashSet<>();
        cardSet.addAll(suspects);
        cardSet.addAll(weapons);
        cardSet.addAll(rooms);

        return cardSetToArray(cardSet);
    }

    public static Card[] cardSetToArray(Set<Card> cardSet){
        Card[] cardArray = new Card[cardSet.size()]; int i = 0;
        for (Card c: cardSet){
            cardArray[i] = c;
            i++;
        }

        return cardArray;
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

    private static Set<Card> getAllCards(){
        Set<Card> set = new HashSet<>(suspects);
        set.addAll(weapons);
        set.addAll(rooms);
        return set;
    }
}
