//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main implements ActionListener {
    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passLabel;
    private static JPasswordField passText;
    private static JButton loginButton;
    private static JButton registerButton;
    private static RegisterScreenGUI registerScreenGUI;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

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
        loginButton.addActionListener(new Main());
        panel.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(110,100,90,25);
        registerButton.addActionListener(new Main());
        panel.add(registerButton);

        registerScreenGUI = new RegisterScreenGUI();
        registerScreenGUI.setVisible(false);



        frame.setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());


        if (e.getActionCommand().toUpperCase().equals("REGISTER")){
            registerScreenGUI.setVisible(true);
            frame.setVisible(false);
        } else if (e.getActionCommand().toUpperCase().equals("LOGIN")){
            System.out.println("Currently in login");
            String user = userText.getText();
            String pass = passText.getText();
            if (user.equals("user") && pass.equals("pass")){
                JOptionPane.showMessageDialog(frame, "Login success");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid usernamme or password");

            }
        }

    }
}