import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;

public class GameSetup {
    public JFrame f;
    public JTextField inputPlayers;
    public JComboBox inputCards;
    public boolean collect = false;

    public GameSetup() {
        f = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        placeComponents(panel);

        f.add(panel);
        f.pack();
        f.setSize(300,200);
        f.setVisible(true);

    }

    public PackagedSetupInfo collectData(){
        while (true) {
            Player[] players = stringToPlayers(inputPlayers.getText());
            Card[] startingCards = getCards();
            PackagedSetupInfo gameInfo = new PackagedSetupInfo(players, startingCards)
            if (players.length >= 3 && collect) {
                System.out.println("something");
                f.dispose();
                return gameInfo;
            }
            else {collect = false;}
        }
    }

    private Player[] stringToPlayers(String playerString){
        playerString = playerString.replaceAll("\\s+","");
        // convert String[] array to Player[] array
        Player[] players = Arrays.stream(playerString.split(","))
                .map(Player::new)
                .toArray(Player[]::new);

        return players;
    }

    private Card[] getCards(){
        return new Card[] {(Card) inputCards.getSelectedItem()};
    }

    private void placeComponents(JPanel panel){
        // add players
        JLabel inputHint = new JLabel("Players: ");
        inputHint.setBounds(10,20,80,25);
        panel.add(inputHint);
        this.inputPlayers = new JTextField();
        inputPlayers.setBounds(80,20,165,25);
        panel.add(inputPlayers);
        // add starting cards will need loop fix later
        JLabel cardInputHint = new JLabel("Cards: ");
        cardInputHint.setBounds(10,50,80,25);
        panel.add(cardInputHint);
        this.inputCards = new JComboBox(getArrayOfAllCards());
        inputCards.setBounds(80,50,165,25);
        panel.add(inputCards);
        // add start button
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e->collect = true);
        startButton.setBounds(10, 80, 80, 25);
        panel.add(startButton);

    }

    private Card[] getArrayOfAllCards(){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (CardValue c : Card.suspects){
            cards.add(new Card(c));
        }
        for (CardValue c : Card.rooms){
            cards.add(new Card(c));
        }
        for (CardValue c : Card.weapons){
            cards.add(new Card(c));
        }

        // manual copy cause problems
        Card[] cardsArray = new Card[cards.size()];
        for (int i = 0; i < cards.size(); i++){
            cardsArray[i] = cards.get(i);
        }
        return cardsArray;
    }

}
