import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<CardValue> allCards = new ArrayList<>(Arrays.asList(CardValue.values()));
        Card card = new Card(CardValue.REVOLVER);

        Game game = new Game(false);
        // game.players[1].active = false;game.players[0].active = false;
        // for (int i = 1; i < 5; i++){
        //     System.out.println(game.players[game.getNextTurn()]);
        // }

        game.gameUI.testSet("✅", game.players[0],new Card(CardValue.SCARLET));
        game.gameUI.refresh();
    }
}