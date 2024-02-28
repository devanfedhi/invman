import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterScreenGUI extends JFrame implements ActionListener {
    private static JPanel panel = new JPanel();
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passLabel;
    private static JPasswordField passText;
    private static JButton registerButton;

    public RegisterScreenGUI() {
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
        registerButton.addActionListener(this);
        panel.add(registerButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
