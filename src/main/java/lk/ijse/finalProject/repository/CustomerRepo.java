package lk.ijse.finalProject.repository;

import com.mysql.cj.util.DnsSrv;
import javafx.scene.control.Alert;
import lk.ijse.finalProject.DB.Dbconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {

    public static void registerCustomer(String userId, String username, String password, String repassword) throws SQLException {
        if (password.equals(repassword)) {
            String sql = "INSERT INTO User VALUES(? , ?, ?)";
            PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, userId);
            pstm.setObject(2, username);
            pstm.setObject(3, password);
            int i = pstm.executeUpdate();
            if (i > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "User save sucessfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Cant save details").show();
            }
        } else{
            new Alert(Alert.AlertType.ERROR,"Please chech your password!").show();
        }
    }

    public static String getCurrentId() throws SQLException {
        String sql ="SELECT user_id FROM User ORDER BY user_id DESC LIMIT 1";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String dbUser = resultSet.getString("user_id");
            return dbUser;
        }
        return null;
    }

    public static String getNextAvailableId(String currentId) {
        if (currentId != null){
            String[] split = currentId.split("U");
            int idNum = Integer.parseInt(split[1]);
            return "U" + ++idNum;
        }
        return "U1";

    }

    public static List<String> getId() throws SQLException {
        List<String> cusId = new ArrayList<>();
        String sql = "SELECT customer_id FROM Customer";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            cusId.add(resultSet.getString(1));
        }
        return cusId;
    }
}
