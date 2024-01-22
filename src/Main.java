import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        UserInterface UI = new UserInterface();

        ArrayList<CardValue> allCards = new ArrayList<>(Arrays.asList(CardValue.values()));

        Card card = new Card(CardValue.REVOLVER);
        System.out.println(card.type);
        System.out.println(card.value);

        Game game = new Game();
        game.players[1].active = false;game.players[0].active = false;
        for (int i = 1; i < 5; i++){
            System.out.println(game.players[game.getNextTurn()]);
        }
    }
}