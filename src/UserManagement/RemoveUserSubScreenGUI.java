package UserManagement;


import GeneralScreens.HomeScreenGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RemoveUserSubScreenGUI extends JFrame {
    private static RemoveUserSubScreenGUI singleInstance = null;

    private static JLabel userRemoveLabel;
    private static JTextField userRemoveText;
    private static JButton removeButton;
    private static JButton cancelButton;

    private static JPanel removeUserPanel =  new JPanel();

    private RemoveUserSubScreenGUI(){
        super();
        this.setup();

    }

    public static RemoveUserSubScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new RemoveUserSubScreenGUI();
        }

        return singleInstance;
    }


    public void setup(){

        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        removeUserPanel.setLayout(null);


        userRemoveLabel = new JLabel("User");
        userRemoveLabel.setBounds(10,20,80,25);
        removeUserPanel.add(userRemoveLabel);

        userRemoveText = new JTextField();
        userRemoveText.setBounds(100,20,165,25);
        removeUserPanel.add(userRemoveText);

        removeButton = new JButton("Remove");
        removeButton.setBounds(10,60,90,25);
        removeUserPanel.add(removeButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(120,60,90,25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveUserSubScreenGUI.getInstance().dispose();
                List<JButton> allButtons = UserManagementScreenGUI.getInstance().getAllButtons();
                for (int i = 0; i < allButtons.size(); i++) {
                    allButtons.get(i).setEnabled(true);
                }
            }
        });
        removeUserPanel.add(cancelButton);

        this.add(removeUserPanel);
    }

}
