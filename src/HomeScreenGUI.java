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


        userManagementButton = new JButton("User",new ImageIcon("images/user.png"));
        userManagementButton.setBounds(10,10,150,60);
        userManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(userManagementButton);

        customerButton = new JButton("Customer",new ImageIcon("images/customer.png"));
        customerButton.setBounds(10,90,150,60);
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(customerButton);

        categoryButton = new JButton("Category",new ImageIcon("images/category.png"));
        categoryButton.setBounds(10,170,150,60);
        categoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(categoryButton);

        productButton = new JButton("Product",new ImageIcon("images/product.png"));
        productButton.setBounds(10,250,150,60);
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(productButton);
    }

    public static HomeScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new HomeScreenGUI();
        }

        return singleInstance;
    }

}
