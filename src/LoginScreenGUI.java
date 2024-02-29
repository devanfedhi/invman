import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginScreenGUI extends JFrame implements ActionListener {
    private static LoginScreenGUI singleInstance = null;
    private static JPanel panel = new JPanel();
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passLabel;
    private static JPasswordField passText;
    private static JButton loginButton;
    private static JButton registerButton;
    private LoginScreenGUI(){
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

        loginButton = new JButton("Login");
        loginButton.setBounds(10,100,90,25);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(110,100,90,25);
        registerButton.addActionListener(this);
        panel.add(registerButton);

    }

    public static LoginScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new LoginScreenGUI();
        }

        return singleInstance;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());


        if (e.getActionCommand().toUpperCase().equals("REGISTER")){
            RegisterScreenGUI.getInstance().setVisible(true);
            this.setVisible(false);
        } else if (e.getActionCommand().toUpperCase().equals("LOGIN")){
            System.out.println("efwef");
            String user = userText.getText();
            String pass = passText.getText();

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema","root","123bombom");
                Statement stmt = connection.createStatement();
                String sql = String.format("SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME = '%s'",user);
                ResultSet resultSet = stmt.executeQuery(sql);
                String result = null;
                boolean userPass = resultSet.next();


                if (!userPass) {
                    result = String.format("User '%s' has not been found.",user);
                } else {
                    String usernameSQL = resultSet.getString("USERNAME");
                    String passwordSQL = resultSet.getString("PASSWORD");

                    if (usernameSQL.equals(user) && passwordSQL.equals(pass)) {
                        result = String.format("User '%s' has successfully logged in.",user);
                    } else {
                        result = String.format("Incorrect username/password");
                    }

                }

                JOptionPane.showMessageDialog(this, result);


            } catch (SQLException ex) {
                String failure = String.format("Connection to server unsuccessful. Login failed. Try again");
                JOptionPane.showMessageDialog(this, failure);
            }

        }
    }
}
