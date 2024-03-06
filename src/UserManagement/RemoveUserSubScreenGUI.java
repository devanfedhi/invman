package UserManagement;


import GeneralScreens.HomeScreenGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
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

    public void refresh(){
        userRemoveText.setText("");
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
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                Statement stmt = null;
                String sql = null;
                String result = null;

                String user = userRemoveText.getText();

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

                    if (!userPass) {
                        result = String.format("User does not exists. Choose another username",user);
                        JOptionPane.showMessageDialog(AddUserSubScreenGUI.getInstance(), result);
                        return;
                    }

                    stmt = connection.createStatement();
                    sql = String.format("DELETE FROM USERS WHERE USERNAME = '%s'",user);
                    stmt.executeUpdate(sql);

                    result = String.format("User %s has been removed successfully", user);
                    JOptionPane.showMessageDialog(AddUserSubScreenGUI.getInstance(), result);

                    List<JButton> allButtons = UserManagementScreenGUI.getInstance().getAllButtons();
                    for (int i = 0; i < allButtons.size(); i++) {
                        allButtons.get(i).setEnabled(true);
                    }

                    RemoveUserSubScreenGUI.getInstance().dispose();

                    UserManagementScreenGUI.getInstance().refreshTable();


                } catch (SQLException ex) {
                    result = String.format("User has not been registered successfully. Try again");
                    JOptionPane.showMessageDialog(AddUserSubScreenGUI.getInstance(), result);
                }

                
            }
        });
        removeUserPanel.add(removeButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(120,60,90,25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<JButton> allButtons = UserManagementScreenGUI.getInstance().getAllButtons();
                for (int i = 0; i < allButtons.size(); i++) {
                    allButtons.get(i).setEnabled(true);
                }
                RemoveUserSubScreenGUI.getInstance().dispose();
            }
        });
        removeUserPanel.add(cancelButton);

        this.add(removeUserPanel);
    }

}
