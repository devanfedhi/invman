//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import GeneralScreens.HomeScreenGUI;
import GeneralScreens.LoginScreenGUI;
import GeneralScreens.RegisterScreenGUI;

public class Main {

    public static void main(String[] args) {

        LoginScreenGUI loginScreenGUI = LoginScreenGUI.getInstance();
        loginScreenGUI.setVisible(false);

        RegisterScreenGUI registerScreenGUI = RegisterScreenGUI.getInstance();
        registerScreenGUI.setVisible(false);

        HomeScreenGUI homeScreenGUI = HomeScreenGUI.getInstance();
        homeScreenGUI.setVisible(true);


    }


}