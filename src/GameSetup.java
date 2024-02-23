import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;

public class GameSetup {
    public JFrame f;
    public JTextField inputPlayers;
    public ArrayList<JComboBox<Card>> inputCardsList;
    private final int noOfPlayers;
    public boolean collect = false;

    public GameSetup(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
        inputCardsList = new ArrayList<>();

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
            System.out.print("");
            if (collect){
                System.out.println("attempting collect");
                Player[] players = stringToPlayers(inputPlayers.getText());
                Card[] startingCards = getCards();
                PackagedSetupInfo gameInfo = new PackagedSetupInfo(players, startingCards, noOfPlayers);
                if (gameInfo.validate()) {
                    f.dispose();
                    return gameInfo;
                }
                else {collect = false;}
            }
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
        Card[] cards = new Card[inputCardsList.size()];
        for (int i = 0; i < cards.length; i++){
            cards[i] = (Card) inputCardsList.get(i).getSelectedItem();
        }
        return cards;
    }

    private void addNewCardSelection(JPanel panel, int y){
        JComboBox<Card> newDropDown= new JComboBox<>(getArrayOfAllCards());
        inputCardsList.add(newDropDown);
        newDropDown.setBounds(80,y,165,25);
        newDropDown.setSelectedIndex(0);
        panel.add(newDropDown);

    }

    private void addAllCardSelections(JPanel panel, int baseY){
        int yModifier = 30;

        JLabel cardInputHint = new JLabel("Cards: ");
        cardInputHint.setBounds(10,baseY,80,25);
        panel.add(cardInputHint);

        for (int i=0; i < noOfPlayers; i++){
            addNewCardSelection(panel,baseY+yModifier*i);
        }

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e-> collect = true);
        startButton.setBounds(10, baseY+yModifier*(noOfPlayers), 80, 25);
        panel.add(startButton);
    }

    private void placeComponents(JPanel panel){
        // add players
        JLabel inputHint = new JLabel("Players: ");
        inputHint.setBounds(10,20,80,25);
        panel.add(inputHint);
        this.inputPlayers = new JTextField();
        inputPlayers.setBounds(80,20,165,25);
        panel.add(inputPlayers);
        // add starting card selections and finish button
        addAllCardSelections(panel, 50);

    }

    private Card[] getArrayOfAllCards(){
        ArrayList<Card> cards = new ArrayList<>();
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
