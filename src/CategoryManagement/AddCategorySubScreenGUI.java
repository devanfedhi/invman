package CategoryManagement;


import GeneralScreens.HomeScreenGUI;
import GeneralScreens.LoginScreenGUI;
import GeneralScreens.RegisterScreenGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class AddCategorySubScreenGUI extends JFrame {
    private static AddCategorySubScreenGUI singleInstance = null;

    private static JLabel categoryLabel;
    private static JTextField categoryText;
    private static JLabel descLabel;
    private static JTextField descText;
    private static JButton cancelButton;
    private static JButton registerButton;

    private static JPanel addCategoryPanel = new JPanel();

    private AddCategorySubScreenGUI(){
        super();
        this.setup();

    }

    public static AddCategorySubScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new AddCategorySubScreenGUI();
        }

        return singleInstance;
    }

    public void refresh(){
        categoryText.setText("");
        descText.setText("");
    }


    public void setup(){

        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addCategoryPanel.setLayout(null);


        categoryLabel = new JLabel("Category Name");
        categoryLabel.setBounds(10,20,80,25);
        addCategoryPanel.add(categoryLabel);

        categoryText = new JTextField();
        categoryText.setBounds(100,20,165,25);
        addCategoryPanel.add(categoryText);

        descLabel = new JLabel("Category Description");
        descLabel.setBounds(10,60,80,25);
        addCategoryPanel.add(descLabel);

        descText = new JPasswordField();
        descText.setBounds(100,60,165,25);
        addCategoryPanel.add(descText);

        registerButton = new JButton("Register");
        registerButton.setBounds(10,100,90,25);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                Statement stmt = null;
                String sql = null;
                String result = null;

                String categoryName = categoryText.getText();
                String categoryDesc = descText.getText();

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema","root","123bombom");
                } catch (SQLException ex){
                    result = String.format("Could not connect to SQL database");
                    JOptionPane.showMessageDialog(AddCategorySubScreenGUI.getInstance(), result);
                    return;
                }


                try {

                    stmt = connection.createStatement();
                    sql = String.format("SELECT CATEGORY_NAME FROM CATEGORY WHERE CATEGORY_NAME = '%s'",categoryName);

                    ResultSet resultSet = stmt.executeQuery(sql);

                    boolean catExists = resultSet.next();

                    if (catExists) {
                        result = String.format("Category '%s' already exists. Choose another category name",categoryName);
                        JOptionPane.showMessageDialog(AddCategorySubScreenGUI.getInstance(), result);
                        return;
                    }

                    stmt = connection.createStatement();
                    sql = String.format("INSERT INTO CATEGORY (CATEGORY_NAME, CATEGORY_DESC) VALUES ('%s','%s')",categoryName,categoryDesc);
                    stmt.executeUpdate(sql);

                    result = String.format("Category %s has been registered successfully", categoryName);
                    JOptionPane.showMessageDialog(AddCategorySubScreenGUI.getInstance(), result);

                    List<JButton> allButtons = CategoryManagementScreenGUI.getInstance().getAllButtons();
                    for (int i = 0; i < allButtons.size(); i++) {
                        allButtons.get(i).setEnabled(true);
                    }

                    AddCategorySubScreenGUI.getInstance().dispose();

                    CategoryManagementScreenGUI.getInstance().refreshTable();


                } catch (SQLException ex) {
                    result = String.format("Category has not been registered successfully. Try again");
                    JOptionPane.showMessageDialog(AddCategorySubScreenGUI.getInstance(), result);
                }
            }
        });
        addCategoryPanel.add(registerButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(120,100,90,25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<JButton> allButtons = CategoryManagementScreenGUI.getInstance().getAllButtons();
                for (int i = 0; i < allButtons.size(); i++) {
                    allButtons.get(i).setEnabled(true);
                }
                AddCategorySubScreenGUI.getInstance().dispose();

            }
        });
        addCategoryPanel.add(cancelButton);

        this.add(addCategoryPanel);
    }

}
