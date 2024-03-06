package CategoryManagement;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class RemoveCategorySubScreenGUI extends JFrame {
    private static RemoveCategorySubScreenGUI singleInstance = null;

    private static JLabel categoryRemoveLabel;
    private static JTextField categoryRemoveText;
    private static JButton removeButton;
    private static JButton cancelButton;

    private static JPanel removeCategoryPanel =  new JPanel();

    private RemoveCategorySubScreenGUI(){
        super();
        this.setup();

    }

    public static RemoveCategorySubScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new RemoveCategorySubScreenGUI();
        }

        return singleInstance;
    }

    public void refresh(){
        categoryRemoveText.setText("");
    }


    public void setup(){

        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        removeCategoryPanel.setLayout(null);


        categoryRemoveLabel = new JLabel("Category Name");
        categoryRemoveLabel.setBounds(10,20,160,25);
        removeCategoryPanel.add(categoryRemoveLabel);

        categoryRemoveText = new JTextField();
        categoryRemoveText.setBounds(180,20,165,25);
        removeCategoryPanel.add(categoryRemoveText);

        removeButton = new JButton("Remove");
        removeButton.setBounds(10,60,90,25);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                Statement stmt = null;
                String sql = null;
                String result = null;

                String category = categoryRemoveText.getText();

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema","root","123bombom");
                } catch (SQLException ex){
                    result = String.format("Could not connect to SQL database");
                    JOptionPane.showMessageDialog(RemoveCategorySubScreenGUI.getInstance(), result);
                    return;
                }


                try {
                    stmt = connection.createStatement();
                    sql = String.format("SELECT CATEGORY_NAME FROM CATEGORY WHERE CATEGORY_NAME = '%s'",category);
                    ResultSet resultSet = stmt.executeQuery(sql);
                    boolean catExists = resultSet.next();

                    if (!catExists) {
                        result = String.format("Category does not exist. Choose another category",category);
                        JOptionPane.showMessageDialog(RemoveCategorySubScreenGUI.getInstance(), result);
                        return;
                    }

                    stmt = connection.createStatement();
                    sql = String.format("DELETE FROM CATEGORY WHERE CATEGORY_NAME = '%s'",category);
                    stmt.executeUpdate(sql);

                    result = String.format("Category %s has been removed successfully", category);
                    JOptionPane.showMessageDialog(RemoveCategorySubScreenGUI.getInstance(), result);

                    List<JButton> allButtons = CategoryManagementScreenGUI.getInstance().getAllButtons();
                    for (int i = 0; i < allButtons.size(); i++) {
                        allButtons.get(i).setEnabled(true);
                    }

                    RemoveCategorySubScreenGUI.getInstance().dispose();

                    CategoryManagementScreenGUI.getInstance().refreshTable();


                } catch (SQLException ex) {
                    result = String.format("Category has not been deleted successfully. Try again");
                    JOptionPane.showMessageDialog(RemoveCategorySubScreenGUI.getInstance(), result);
                }


            }
        });
        removeCategoryPanel.add(removeButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(120,60,90,25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<JButton> allButtons = CategoryManagementScreenGUI.getInstance().getAllButtons();
                for (int i = 0; i < allButtons.size(); i++) {
                    allButtons.get(i).setEnabled(true);
                }
                RemoveCategorySubScreenGUI.getInstance().dispose();
            }
        });
        removeCategoryPanel.add(cancelButton);

        this.add(removeCategoryPanel);
    }

}
