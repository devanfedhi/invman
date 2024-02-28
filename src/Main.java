//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_schema","root","123bombom");

        } catch (SQLException e) {
            e.printStackTrace();
        }



//        try {
//
//            statement = connection.createStatement();
//            String sql = "DELETE FROM USERS WHERE idusers != 1";
//            statement.executeUpdate(sql);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");

            while (resultSet.next()){
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try {
//
//            statement = connection.createStatement();
//            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM USERS");
//
//            while (resultSet1.next()){
//                System.out.println(resultSet1.getString("username"));
//                System.out.println(resultSet1.getString("password"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }




    }
}