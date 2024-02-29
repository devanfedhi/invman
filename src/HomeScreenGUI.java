import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class HomeScreenGUI extends JFrame {
    private static HomeScreenGUI singleInstance = null;
    private static JPanel panel = new JPanel();
    private static JButton userManagementButton;
    private static JButton categoryButton;
    private static JButton productButton;
    private static JButton customerButton;
    private static JButton makeOrderButton;
    private static JButton viewOrderButton;
    private static JButton logoutButton;


    private HomeScreenGUI(){
        super();

        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);

        ImageIcon user = new ImageIcon("user.png");

        userManagementButton = new JButton(user);
        userManagementButton.setBounds(10,10,60,60);
        userManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(userManagementButton);

    }

    public static HomeScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new HomeScreenGUI();
        }

        return singleInstance;
    }

}
