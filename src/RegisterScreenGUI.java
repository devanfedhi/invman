import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterScreenGUI extends JFrame {
    private static RegisterScreenGUI singleInstance = null;
    private static JPanel panel = new JPanel();
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passLabel;
    private static JPasswordField passText;
    private static JButton registerButton;


    private RegisterScreenGUI() {
        super();

        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        passLabel = new JLabel("Password");
        passLabel.setBounds(10,60,80,25);
        panel.add(passLabel);

        passText = new JPasswordField();
        passText.setBounds(100,60,165,25);
        panel.add(passText);

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
                    String error = String.format("Could not connect to SQL database");
                    JOptionPane.showMessageDialog(RegisterScreenGUI.getInstance(), error);
                    return;
                }


                try {
                    stmt = connection.createStatement();
                    sql = String.format("SELECT USERNAME FROM USERS WHERE USERNAME = '%s'",user);
                    ResultSet resultSet = stmt.executeQuery(sql);
                    boolean userPass = resultSet.next();

                    if (userPass) {
                        result = String.format("User '%s' already exists. Choose another username",user);
                        JOptionPane.showMessageDialog(RegisterScreenGUI.getInstance(), result);
                        return;
                    }

                    stmt = connection.createStatement();
                    sql = String.format("INSERT INTO USERS (username, password) VALUES ('%s','%s')",user,pass);
                    stmt.executeUpdate(sql);

                    LoginScreenGUI.getInstance().setVisible(true);
                    RegisterScreenGUI.getInstance().setVisible(false);

                    result = String.format("User %s has been registered successfully", user);
                    JOptionPane.showMessageDialog(RegisterScreenGUI.getInstance(), result);


                } catch (SQLException ex) {
                    result = String.format("User has not been registered successfully. Try again");
                    JOptionPane.showMessageDialog(RegisterScreenGUI.getInstance(), result);
                }
            }
        });
        panel.add(registerButton);

    }

    public static RegisterScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new RegisterScreenGUI();
        }

        return singleInstance;
    }

}
