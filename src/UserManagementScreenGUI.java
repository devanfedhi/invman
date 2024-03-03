import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagementScreenGUI extends JFrame {
    private static UserManagementScreenGUI singleInstance = null;
    private static JPanel panel = new JPanel();

    private static JButton homeButton;
    private static JButton addUserButton;
    private static JButton removeUserButton;

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel userRemoveLabel;
    private static JTextField userRemoveText;
    private static JLabel passLabel;
    private static JPasswordField passText;
    private static JButton registerButton;
    private static JButton removeButton;

    private static JFrame changeUserFrame;
    private static JFrame removeUserFrame;
    private static JPanel changeUserPanel = new JPanel();
    private static JPanel removeUserPanel =  new JPanel();





    private UserManagementScreenGUI(){
        super();

        this.setupMain();

        this.setupAddUser();

        this.setupRemoveUser();



//        Connection connection = null;
//
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema","root","123bombom");
//
//        } catch (SQLException e) {
//            String result = String.format("Connection to server unsuccessful. Login failed. Try again");
//            JOptionPane.showMessageDialog(UserManagementScreenGUI.getInstance(), result);
//        }

    }

    public static UserManagementScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new UserManagementScreenGUI();
        }

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
                changeUserFrame.setVisible(true);

            }
        });
        panel.add(addUserButton);

        removeUserButton = new JButton("Remove User",new ImageIcon("images/remuser.png"));
        removeUserButton.setBounds(10,170,150,60);
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeUserFrame.setVisible(true);
            }
        });
        panel.add(removeUserButton);

        JTable table = createTable();
        JScrollPane tableWithScroll = new JScrollPane(table);
        tableWithScroll.setBounds(200,10,300,500);
        panel.add(tableWithScroll);

        this.add(panel);

    }

    public void setupAddUser(){

        changeUserFrame = new JFrame();
        changeUserFrame.setSize(400,400);
        changeUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        changeUserPanel.setLayout(null);


        userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        changeUserPanel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(100,20,165,25);
        changeUserPanel.add(userText);

        passLabel = new JLabel("Password");
        passLabel.setBounds(10,60,80,25);
        changeUserPanel.add(passLabel);

        passText = new JPasswordField();
        passText.setBounds(100,60,165,25);
        changeUserPanel.add(passText);

        registerButton = new JButton("Register");
        registerButton.setBounds(10,100,90,25);
        changeUserPanel.add(registerButton);

        changeUserFrame.add(changeUserPanel);
    }

    public void setupRemoveUser(){

        removeUserFrame = new JFrame();
        removeUserFrame.setSize(400,400);
        removeUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        removeUserPanel.setLayout(null);


        userRemoveLabel = new JLabel("User");
        userRemoveLabel.setBounds(10,20,80,25);
        removeUserPanel.add(userRemoveLabel);

        userRemoveText = new JTextField();
        userRemoveText.setBounds(100,20,165,25);
        removeUserPanel.add(userRemoveText);

        removeButton = new JButton("Remove");
        removeButton.setBounds(10,60,90,25);
        removeUserPanel.add(removeButton);

        removeUserFrame.add(removeUserPanel);
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

            table = new JTable(model);
            table.setFillsViewportHeight(true);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;


    }

}
