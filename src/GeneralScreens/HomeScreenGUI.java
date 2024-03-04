package GeneralScreens;

import UserManagement.UserManagementScreenGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                UserManagementScreenGUI.getInstance().setVisible(true);
                HomeScreenGUI.getInstance().setVisible(false);
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

        makeOrderButton = new JButton("Order",new ImageIcon("images/makeorder.png"));
        makeOrderButton.setBounds(10,330,150,60);
        makeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(makeOrderButton);

        viewOrderButton = new JButton("View Order",new ImageIcon("images/vieworder.png"));
        viewOrderButton.setBounds(10,410,150,60);
        viewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(viewOrderButton);

        logoutButton = new JButton("Logout",new ImageIcon("images/logout.png"));
        logoutButton.setBounds(10,490,150,60);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(logoutButton);
    }

    public static HomeScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new HomeScreenGUI();
        }

        return singleInstance;
    }

}
