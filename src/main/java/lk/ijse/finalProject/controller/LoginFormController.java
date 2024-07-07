package lk.ijse.finalProject.controller;

//import animatefx.animation.FadeIn;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.db.Dbconnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txtusername;
    public TextField txtpassword;
    public AnchorPane rootNode;

    public void loginOnAction(ActionEvent actionEvent) {
        String username = txtusername.getText();
        String password = txtpassword.getText();

        try {
            checkCredintial(username,password);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkCredintial(String username, String password) throws SQLException, IOException {
        String sql = "SELECT user_name,password FROM User WHERE user_name = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,username);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            String dbpassword = resultSet.getString("password");
            if (password.equals(dbpassword)){
                navigateToTheHomePage();
            }else {
                new Alert(Alert.AlertType.ERROR,"Incorrect Password").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR,"User can't find").show();
        }
    }

    private void navigateToTheHomePage() throws IOException {
        BorderPane rootNode = FXMLLoader.load(getClass().getResource("/view/homePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.centerOnScreen();
      //  new FadeIn(rootNode).play();
    }



    public void btnsingUp(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/registerPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Register Page");
        stage.centerOnScreen();
        stage.show();
    }

    public void usernameAction(ActionEvent actionEvent) {
        txtpassword.requestFocus();
    }

    public void passwordonAction(ActionEvent actionEvent) {
        loginOnAction(actionEvent);
    }
}
