package UserManagement;


import GeneralScreens.HomeScreenGUI;
import GeneralScreens.LoginScreenGUI;
import GeneralScreens.RegisterScreenGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class AddUserSubScreenGUI extends JFrame {
    private static AddUserSubScreenGUI singleInstance = null;

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passLabel;
    private static JPasswordField passText;
    private static JButton cancelButton;
    private static JButton registerButton;

    private static JPanel changeUserPanel = new JPanel();

    private AddUserSubScreenGUI(){
        super();
        this.setup();

    }

    public static AddUserSubScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new AddUserSubScreenGUI();
        }

        return singleInstance;
    }


    public void setup(){

        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                Statement stmt = null;
                String sql = null;
                String result = null;

                String user = userText.getText();
                String pass = passText.getText();

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema","root","123bombom");
                } catch (SQLException ex){
                    result = String.format("Could not connect to SQL database");
                    JOptionPane.showMessageDialog(AddUserSubScreenGUI.getInstance(), result);
                    return;
                }


                try {
                    stmt = connection.createStatement();
                    sql = String.format("SELECT USERNAME FROM USERS WHERE USERNAME = '%s'",user);
                    ResultSet resultSet = stmt.executeQuery(sql);
                    boolean userPass = resultSet.next();

                    if (userPass) {
                        result = String.format("User '%s' already exists. Choose another username",user);
                        JOptionPane.showMessageDialog(AddUserSubScreenGUI.getInstance(), result);
                        return;
                    }

                    stmt = connection.createStatement();
                    sql = String.format("INSERT INTO USERS (username, password) VALUES ('%s','%s')",user,pass);
                    stmt.executeUpdate(sql);

                    result = String.format("User %s has been registered successfully", user);
                    JOptionPane.showMessageDialog(AddUserSubScreenGUI.getInstance(), result);

                    List<JButton> allButtons = UserManagementScreenGUI.getInstance().getAllButtons();
                    for (int i = 0; i < allButtons.size(); i++) {
                        allButtons.get(i).setEnabled(true);
                    }

                    AddUserSubScreenGUI.getInstance().dispose();

                    UserManagementScreenGUI.getInstance().refreshTable();


                } catch (SQLException ex) {
                    result = String.format("User has not been registered successfully. Try again");
                    JOptionPane.showMessageDialog(AddUserSubScreenGUI.getInstance(), result);
                }
            }
        });
        changeUserPanel.add(registerButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(120,100,90,25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<JButton> allButtons = UserManagementScreenGUI.getInstance().getAllButtons();
                for (int i = 0; i < allButtons.size(); i++) {
                    allButtons.get(i).setEnabled(true);
                }
                AddUserSubScreenGUI.getInstance().dispose();

            }
        });
        changeUserPanel.add(cancelButton);

        this.add(changeUserPanel);
    }

}
