package UserManagement;


import GeneralScreens.HomeScreenGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        changeUserPanel.add(registerButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(120,100,90,25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserSubScreenGUI.getInstance().dispose();
                List<JButton> allButtons = UserManagementScreenGUI.getInstance().getAllButtons();
                for (int i = 0; i < allButtons.size(); i++) {
                    allButtons.get(i).setEnabled(true);
                }
            }
        });
        changeUserPanel.add(cancelButton);

        this.add(changeUserPanel);
    }

}
