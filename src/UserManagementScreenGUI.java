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
    private static JButton addUserButton;
    private static JButton removeUserButton;
    private static JPanel addUserPanel = new JPanel();

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passLabel;
    private static JPasswordField passText;
    private static JButton registerButton;



    private UserManagementScreenGUI(){
        super();

        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);
        addUserPanel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(550,20,80,25);
        addUserPanel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(640,20,165,25);
        addUserPanel.add(userText);

        passLabel = new JLabel("Password");
        passLabel.setBounds(550,60,80,25);
        addUserPanel.add(passLabel);

        passText = new JPasswordField();
        passText.setBounds(640,60,165,25);
        addUserPanel.add(passText);

        registerButton = new JButton("Register");
        registerButton.setBounds(550,100,90,25);
        addUserPanel.add(registerButton);
//        Connection connection = null;
//
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema","root","123bombom");
//
//        } catch (SQLException e) {
//            String result = String.format("Connection to server unsuccessful. Login failed. Try again");
//            JOptionPane.showMessageDialog(UserManagementScreenGUI.getInstance(), result);
//        }


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
                UserManagementScreenGUI.getInstance().add(addUserPanel);
                UserManagementScreenGUI.getInstance().setVisible(true);

            }
        });
        panel.add(addUserButton);

        removeUserButton = new JButton("Remove User",new ImageIcon("images/remuser.png"));
        removeUserButton.setBounds(10,170,150,60);
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeScreenGUI.getInstance().setVisible(true);
                UserManagementScreenGUI.getInstance().setVisible(false);
            }
        });
        panel.add(removeUserButton);

        JTable table = createTable();
        JScrollPane tableWithScroll = new JScrollPane(table);
        tableWithScroll.setBounds(200,10,300,500);
        panel.add(tableWithScroll);

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

            table = new JTable(model);
            table.setFillsViewportHeight(true);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;


    }

}
