import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserInterface {
    public JFrame f = new JFrame();
    public UserInterface(Player[] players) {
//        JButton b = new JButton("click");//creating instance of JButton
//        b.setBounds(130, 100, 100, 40);//x axis, y axis, width, height
//
//        f.add(b);//adding button in JFrame
//
//        f.setSize(400, 500);//400 width and 500 height
//        f.setLayout(null);//using no layout managers
//        f.setVisible(true);//making the frame visible

        // initialise table
        Player[] columnNames = generateColumns(players);
        String[][] data = {};

        DefaultTableModel model = new DefaultTableModel();
        JTable t = new JTable(model);
        model.addColumn(columnNames);

        JScrollPane sp = new JScrollPane(t);
        f.add(sp);
        f.setSize(500, 200);
        f.setVisible(true);

        //add all rows
    }

    private Player[] generateColumns(Player[] players){
        Player[] columns = new Player[players.length+1];
        columns[0] = new Player("Mansion");
        System.arraycopy(players, 0, columns, 1, players.length);
        return columns;

    }
}
