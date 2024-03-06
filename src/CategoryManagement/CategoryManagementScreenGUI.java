package CategoryManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import GeneralScreens.HomeScreenGUI;
import Utilities.MultiLineCellRenderer;

public class CategoryManagementScreenGUI extends JFrame {
    private static CategoryManagementScreenGUI singleInstance = null;
    private static JPanel panel = new JPanel();
    private static int baseRowHeight = 16;
    private static int baseRowWidth = 134;


    private static JButton homeButton;
    private static JButton addCategoryButton;
    private static JButton removeCategoryButton;
    private static JTable table;
    private static DefaultTableModel model;

    private List<JButton> allButtons = new ArrayList<>();


    private CategoryManagementScreenGUI(){
        super();

        this.setupMain();

    }

    public static CategoryManagementScreenGUI getInstance() {
        if (singleInstance == null) {
            singleInstance = new CategoryManagementScreenGUI();
        }

        return singleInstance;
    }

    public void setupMain(){
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(null);

        homeButton = new JButton("Home",new ImageIcon("images/home.png"));
        homeButton.setBounds(10,10,150,60);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeScreenGUI.getInstance().setVisible(true);
                CategoryManagementScreenGUI.getInstance().dispose();
            }
        });
        panel.add(homeButton);

        addCategoryButton = new JButton("Add Category",new ImageIcon("images/adduser.png"));
        addCategoryButton.setBounds(10,90,150,60);
        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCategorySubScreenGUI.getInstance().setVisible(true);
                for (int i = 0; i < allButtons.size(); i++) {

                    allButtons.get(i).setEnabled(false);
                }
                AddCategorySubScreenGUI.getInstance().refresh();

            }
        });
        panel.add(addCategoryButton);

        removeCategoryButton = new JButton("Remove Category",new ImageIcon("images/remuser.png"));
        removeCategoryButton.setBounds(10,170,150,60);
        removeCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(removeCategoryButton);

        allButtons.add(homeButton);
        allButtons.add(addCategoryButton);
        allButtons.add(removeCategoryButton);


        table = createTable();
        table.setDefaultEditor(Object.class, null);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.getColumnModel().getColumn(0).setPreferredWidth(160);
        table.getColumnModel().getColumn(1).setPreferredWidth(800);
        table.getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());

        JScrollPane tableWithScroll = new JScrollPane(table);
        tableWithScroll.setBounds(200,10,960,500);
        panel.add(tableWithScroll);

        this.add(panel);

    }


    public static JTable createTable() {
        JTable table = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema","root","123bombom");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CATEGORY_NAME, CATEGORY_DESC FROM CATEGORY");

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            model = new DefaultTableModel();

            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);
            String CATEGORY_NAME,CATEGORY_DESC;
            while (resultSet.next()) {
                CATEGORY_NAME = resultSet.getString("CATEGORY_NAME");
                CATEGORY_DESC = resultSet.getString("CATEGORY_DESC");
                String[] row = {CATEGORY_NAME,CATEGORY_DESC};
                model.addRow(row);
            }

            table = new JTable(model);

            table.setFillsViewportHeight(true);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;


    }

    public void refreshTable() {
        model.setRowCount(0);

        // Load new data
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema", "root", "123bombom");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT CATEGORY_NAME, CATEGORY_DESC FROM CATEGORY")) {

            while (resultSet.next()) {
                String CATEGORY_NAME = resultSet.getString("CATEGORY_NAME");
                String CATEGORY_DESC = resultSet.getString("CATEGORY_DESC");
                String[] row = {CATEGORY_NAME,CATEGORY_DESC};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<JButton> getAllButtons() {
        return allButtons;
    }

}
