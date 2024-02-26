import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class UserInterface {
    public JFrame f = new JFrame();
    private DefaultTableModel model;
    public ArrayList<JComboBox<Card>> inputCardsList;
    private final HashMap<Player,Integer> playerToColumn;
    private final HashMap<Card,Integer> cardToRow;

    public UserInterface(Game game, Player[] players) {
        //initialise Hashmaps
        playerToColumn = new HashMap<>();
        cardToRow = new HashMap<>();

        // initialise table
        JPanel tablePanel = createTablePanel(players);
        JPanel inputPanel = createInputPanel();

        f.setLayout(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();
        cst.fill = GridBagConstraints.WEST;
        cst.gridx = 0; cst.gridy = 0;
        cst.weightx = 1.0; cst.weighty = 1.0;
        f.add(tablePanel, cst);

        cst = new GridBagConstraints();
        cst.fill = GridBagConstraints.EAST;
        cst.gridx = 1; cst.gridy = 0;
        cst.weightx = 1.0; cst.weighty = 0.0;
        f.add(inputPanel, cst);

        f.pack();
        f.setVisible(true);

    }

    private JPanel createTablePanel(Player[] players) {
        JPanel tablePanel = new JPanel();

        // initialise table
        DefaultTableModel model = new DefaultTableModel();
        this.model = model;
        JTable t = new JTable(model);
        createColumns(model, players);
        addCardData(model, "who?", Card.suspects);
        addCardData(model, "what?", Card.weapons);
        addCardData(model, "where?", Card.rooms);
        JScrollPane sp = new JScrollPane(t);

        // set style
        tablePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        // set and return table
        tablePanel.add(sp,gbc);
        return tablePanel;
    }

    private JPanel createInputPanel(){ // implement rest later
        JPanel inputPanel = new JPanel();
        inputCardsList = new ArrayList<>();

        JLabel cardInputHint = new JLabel("Cards: ");
        cardInputHint.setBounds(10,10,80,25);
        inputPanel.add(cardInputHint);

        addNewCardSelection(inputPanel,"suspects", Card.suspects, 30);
        addNewCardSelection(inputPanel,"weapons", Card.weapons, 60);
        addNewCardSelection(inputPanel, "weapons", Card.rooms, 90);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e-> System.out.println("Button was clicked"));
        startButton.setBounds(10, 120, 80, 25);
        inputPanel.add(startButton);

        return inputPanel;
    }
    private void addNewCardSelection(JPanel panel, String text, Set<Card> cardSet, int y){
        JLabel cardInputHint = new JLabel(text);
        cardInputHint.setBounds(10,y,80,25);
        panel.add(cardInputHint);

        JComboBox<Card> newDropDown= new JComboBox<>(Card.cardSetToArray(cardSet));
        inputCardsList.add(newDropDown);
        newDropDown.setBounds(80,y,165,25);
        newDropDown.setSelectedIndex(0);
        panel.add(newDropDown);

    }

    private void createColumns(DefaultTableModel model,Player[] players){
        model.addColumn(new Player("Mansion"));
        model.addColumn(new Player("|???|"));
        int i = 2;
        for (Player p : players) {
            model.addColumn(p);
            playerToColumn.put(p,i);
            i++;
        }
    }

    private void addCardData(DefaultTableModel model,String title, Set<Card> cardSet){
        model.addRow(new String[] {title});
        int i = model.getRowCount();
        for (Card c : cardSet){
            model.addRow(new Card[] {c});
            cardToRow.put(c, i);
            i++;
        }
    }

    public void updatePlayerColumn(Player player){
        for (Card card: player.doesHave){
            setTableValue("✅", player, card);
        }
        for (Card card: player.notHave){
            setTableValue("❌", player, card);
        }
    }

    public void setTableValue(Object value, Player x, Card y){
        int column = playerToColumn.get(x);
        int row = cardToRow.get(y);
        model.setValueAt(value, row, column);
    }

    // This Column indicates whether a card is definitely Guilty or not
    public void setBigCardColumn(Object value, Card y) {
        int column = 1; // value of Big Card column
        int row = cardToRow.get(y);
        model.setValueAt(value, row, column);
    }

    public void refresh(){
        f.repaint();
    }


}
