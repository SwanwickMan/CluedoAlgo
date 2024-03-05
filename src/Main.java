public class Main {
    public static void main(String[] args) {
        Card card = new Card(CardValue.REVOLVER);

        // test setup of game
        Player[] players = {new Player("1"), new Player("2"),new Player("*3")};
        Card[] startingCards = {new Card(CardValue.REVOLVER), new Card(CardValue.SCARLET), new Card(CardValue.DININGROOM)};
        PackagedSetupInfo setupInfo = new PackagedSetupInfo(players,startingCards, players.length);
        System.out.println(setupInfo.validate());
        Game game = new Game(setupInfo);


//         game.players[2].active = false; game.players[0].active = false;
//         for (int i = 1; i < 10; i++){
//             System.out.println(game.currentPlayer);
//             game.currentPlayer = game.getNextPlayer();
//         }


// boring test code
//        players[2].addNotHasCard(new Card(CardValue.GREEN));
//        players[1].addHasCard(new Card(CardValue.GREEN));
//
//        game.gameUI.setCardColumnGuilty(new Card(CardValue.CANDLESTICK));
//        game.gameUI.updatePlayerColumn(players[1]);
//        game.gameUI.updatePlayerColumn(players[2]);
//        game.gameUI.refresh();

        while (true) game.gameUI.debugUpdateAllPlayerColumns();
    }
}