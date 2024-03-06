package UserManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import GeneralScreens.HomeScreenGUI;

public class UserManagementScreenGUI extends JFrame {
    private static UserManagementScreenGUI singleInstance = null;
    private static JPanel panel = new JPanel();

    private static JButton homeButton;
    private static JButton addUserButton;
    private static JButton removeUserButton;

    private static JTable table;
    private static DefaultTableModel model;

    private List<JButton> allButtons = new ArrayList<>();


    private UserManagementScreenGUI(){
        super();

        this.setupMain();

    }

    public static UserManagementScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new UserManagementScreenGUI();
        }

        return singleInstance;
    }

    public static UserManagementScreenGUI refreshInstance() {
        singleInstance = new UserManagementScreenGUI();

        return singleInstance;
    }

    public void setupMain(){
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        addUserButton = new JButton("Add User",new ImageIcon("images/adduser.png"));
        addUserButton.setBounds(10,90,150,60);
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserSubScreenGUI.getInstance().setVisible(true);
                for (int i = 0; i < allButtons.size(); i++) {

                    allButtons.get(i).setEnabled(false);
                }
                AddUserSubScreenGUI.getInstance().refresh();

            }
        });
        panel.add(addUserButton);

        removeUserButton = new JButton("Remove User",new ImageIcon("images/remuser.png"));
        removeUserButton.setBounds(10,170,150,60);
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveUserSubScreenGUI.getInstance().setVisible(true);
                for (int i = 0; i < allButtons.size(); i++) {

                    allButtons.get(i).setEnabled(false);
                }
                RemoveUserSubScreenGUI.getInstance().refresh();
            }
        });
        panel.add(removeUserButton);

        allButtons.add(homeButton);
        allButtons.add(addUserButton);
        allButtons.add(removeUserButton);


        table = createTable();
        table.setDefaultEditor(Object.class, null);
        JScrollPane tableWithScroll = new JScrollPane(table);
        tableWithScroll.setBounds(200,10,300,500);
        panel.add(tableWithScroll);

        this.add(panel);

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

            model = new DefaultTableModel();

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

            table = new JTable(model);
            table.setFillsViewportHeight(true);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;


    }

    public void refreshTable() {
        model.setRowCount(0);

        // Load new data
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema", "root", "123bombom");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT USERNAME, PASSWORD FROM USERS")) {

            while (resultSet.next()) {
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String[] row = {user,pass};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<JButton> getAllButtons() {
        return allButtons;
    }

}
