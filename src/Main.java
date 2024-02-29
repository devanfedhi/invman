//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {

        LoginScreenGUI loginScreenGUI = LoginScreenGUI.getInstance();
        loginScreenGUI.setVisible(true);

        RegisterScreenGUI registerScreenGUI = RegisterScreenGUI.getInstance();
        registerScreenGUI.setVisible(false);


    }


}