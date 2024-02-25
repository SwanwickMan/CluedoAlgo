import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.HashMap;
import java.util.Set;

public class UserInterface {
    public JFrame f = new JFrame();
    private DefaultTableModel model;
    private HashMap<Player,Integer> playerToColumn;
    private HashMap<Card,Integer> cardToRow;

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
        cst.gridx = 0;
        cst.gridy = 0;
        cst.weightx = 1.0; // --> You miss this for the top panel
        cst.weighty = 1.0;
        f.add(tablePanel, cst);

        cst = new GridBagConstraints();
        cst.fill = GridBagConstraints.EAST;
        cst.gridx = 1;
        cst.gridy = 0;
        cst.weightx = 1.0; // You miss this for the bottom panel
        cst.weighty = 0.0;
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

        JLabel inputHint = new JLabel("Input something: ");
        inputHint.setBounds(10,20,80,25);
        inputPanel.add(inputHint);
        JTextField inputPlayers = new JTextField();
        inputPlayers.setBounds(80,20,165,25);
        inputPanel.add(inputPlayers);

        // set style
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH;

        return inputPanel;
    }

    private void createColumns(DefaultTableModel model,Player[] players){
        model.addColumn(new Player("Mansion"));
        int i = 1;
        for (Player p : players) {
            model.addColumn(p);
            playerToColumn.put(p,i);
            i++;
        }
    }

    private void addCardData(DefaultTableModel model,String title, Set<CardValue> cardSet){
        model.addRow(new String[] {title});
        int i = model.getRowCount();
        for (CardValue c : cardSet){
            Card card = new Card(c);
            model.addRow(new Card[] {card});
            cardToRow.put(card, i);
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

    public void refresh(){
        f.repaint();
    }


}
