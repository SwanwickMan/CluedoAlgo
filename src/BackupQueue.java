import java.util.*;

public class BackupQueue {
    private final LinkedList<Game> queue = new LinkedList<>();
    private static final int maxSize = 6;

    public Game pop(){
        Game game = queue.pollFirst();
        return game;
    }
    public void add(Game game){
        Player[] copiedPlayers = Arrays.stream(game.players)
                .map(Player::new) // Use the copy constructor
                .toArray(Player[]::new);

        Player user = null;
        for (Player p:copiedPlayers){
            if (p.isUser()) { user = p; }
        }

        Game backup = new Game(
             copiedPlayers,
             user,
             game.currentPlayer,
             game.currentPlayerAsked,
             game.gameState,
             game.innocent = new HashSet<>(game.innocent),
             game.maxCardsPerPlayer
        );
        queue.addFirst(backup);
        if (queue.size() > maxSize){ queue.removeLast(); }
    }
}
