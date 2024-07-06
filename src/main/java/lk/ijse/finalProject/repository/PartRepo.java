package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartRepo {
    public static String  getItemId(String name) throws SQLException {
        String sql = "SELECT part_id FROM Part WHERE part_name = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,name);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return  null;
    }

    public static boolean update(String partId, int qty) throws SQLException {
        String sql = "UPDATE Part SET part_qty = part_qty - ? WHERE part_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,qty);
        pstm.setObject(2,partId);
        return pstm.executeUpdate() > 0;
    }

    public static String getCarAmount() throws SQLException {

            String sql ="SELECT COUNT(*) Part_count FROM Part";
            PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                String amount = resultSet.getString("Part_count");
                return amount;
            } else {
                return "0";
            }

    }
}
