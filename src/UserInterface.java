import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;

public class UserInterface {
    public JFrame f = new JFrame();
    public UserInterface(Game game, Player[] players) {
//        JButton b = new JButton("click");//creating instance of JButton
//        b.setBounds(130, 100, 100, 40);//x axis, y axis, width, height
//
//        f.add(b);//adding button in JFrame
//
//        f.setSize(400, 500);//400 width and 500 height
//        f.setLayout(null);//using no layout managers
//        f.setVisible(true);//making the frame visible

        // initialise table
        DefaultTableModel model = new DefaultTableModel();
        JTable t = new JTable(model);
        createColumns(model,players);
        addCardData(model,"who?",Card.suspects);
        addCardData(model,"what?",Card.weapons);
        addCardData(model,"where?",Card.rooms);


        JScrollPane sp = new JScrollPane(t);
        f.add(sp);
        f.setSize(500, 200);
        f.setVisible(true);

        //add all rows


    }

    private void createColumns(DefaultTableModel model,Player[] players){
        model.addColumn(new Player("Mansion"));
        for (Player p : players) {model.addColumn(p);}
    }
    private void addCardData(DefaultTableModel model,String title, Set<CardValue> cardSet){
        model.addRow(new String[] {title});
        for (CardValue c : cardSet){
            model.addRow(new CardValue[] {c});
        }
    }


}
