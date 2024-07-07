package lk.ijse.finalProject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.Util.Regex;
import lk.ijse.finalProject.Util.TextFeld;
import lk.ijse.finalProject.repository.CustomerRepo;

import java.io.IOException;
import java.sql.*;


public class RegisterPageController{

    public TextField txtusermane;
    public TextField txtPassword;
    public TextField txtReEnterPassword;



    public void btnsinginaction(ActionEvent actionEvent) throws IOException {

        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();
        stage.show();
    }

    public void registeractionon(ActionEvent actionEvent) {
        String username = txtusermane.getText();
        String password = txtPassword.getText();
        String rePassword = txtReEnterPassword.getText();
        if (isValied()) {
            try {
                String currentId = CustomerRepo.getCurrentId();
                String nextAvailableId = CustomerRepo.getNextAvailableId(currentId);
                CustomerRepo.registerCustomer(nextAvailableId, username, password, rePassword);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    public void txtCustomerIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeld.txtusermane,txtusermane);

    }
    public void txtpasswordDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeld.txtPassword,txtPassword); // give me a suweetabale regax values for interger values only
    }

    public void txtpasswordretypeOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeld.txtReEnterPassword,txtReEnterPassword);
    }
    public boolean isValied(){
        if (!Regex.setTextColor(TextFeld.txtusermane,txtusermane)) return false;
        if (!Regex.setTextColor(TextFeld.txtPassword,txtPassword)) return false;
        if (!Regex.setTextColor(TextFeld.txtReEnterPassword,txtReEnterPassword)) return false;
        return true;
    }
}
