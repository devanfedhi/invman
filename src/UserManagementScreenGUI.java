import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagementScreenGUI extends JFrame {
    private static UserManagementScreenGUI singleInstance = null;
    private static JPanel panel = new JPanel();

    private static JButton homeButton;


    private UserManagementScreenGUI(){
        super();

        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);


        homeButton = new JButton("Home",new ImageIcon("images/home.png"));
        homeButton.setBounds(10,10,150,60);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeScreenGUI.getInstance().setVisible(true);
                UserManagementScreenGUI.getInstance().setVisible(false);
            }
        });
        panel.add(homeButton);

        Connection connection = null;
        String result = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema","root","123bombom");

        } catch (SQLException e) {
            result = String.format("Connection to server unsuccessful. Login failed. Try again");
            JOptionPane.showMessageDialog(UserManagementScreenGUI.getInstance(), result);
        }

        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT USERNAME, PASSWORD FROM USERS");

            JTable table = createTable();
            JScrollPane tableWithScroll = new JScrollPane(table);
            tableWithScroll.setBounds(200,10,300,100);
            panel.add(tableWithScroll);

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    public static UserManagementScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new UserManagementScreenGUI();
        }

        return singleInstance;
    }

    public static JTable createTable() {
        JTable table = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema","root","123bombom");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT USERNAME, PASSWORD FROM USERS");

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            DefaultTableModel model = new DefaultTableModel();

            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);
            String user,pass;
            while (resultSet.next()) {
                user = resultSet.getString("username");
                pass = resultSet.getString("password");
                String[] row = {user,pass};
                model.addRow(row);
            }

            String[] columnNames = {"username","password"};
            table = new JTable(model);
            table.setFillsViewportHeight(true);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;


    }

}
