import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Set;

public class UserInterface {
    public JFrame f = new JFrame();
    private DefaultTableModel model;
    private HashMap<Player,Integer> playerToColumn;
    private HashMap<Card,Integer> cardToRow;

    public UserInterface(Game game, Player[] players) {
        //initialise Hashmaps
        playerToColumn = new HashMap<Player,Integer>();
        cardToRow = new HashMap<Card,Integer>();

        // initialise table
        DefaultTableModel model = new DefaultTableModel();
        this.model = model;
        JTable t = new JTable(model);
        createColumns(model,players);
        addCardData(model,"who?",Card.suspects);
        addCardData(model,"what?",Card.weapons);
        addCardData(model,"where?",Card.rooms);

        JScrollPane sp = new JScrollPane(t);
        f.add(sp,BorderLayout.PAGE_START);
        f.pack();
        f.setVisible(true);

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

    public void testSet(Object value, Player x, Card y){
        int column = playerToColumn.get(x);
        int row = cardToRow.get(y);
        model.setValueAt(value, row, column);
    }

    public void refresh(){
        f.repaint();
    }


}
