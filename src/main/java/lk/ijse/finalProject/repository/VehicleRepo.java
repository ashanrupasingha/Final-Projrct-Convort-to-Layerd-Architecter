package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepo {
    public static String getCarAmount() throws SQLException {
        String sql ="SELECT COUNT(vehicle_id) FROM Vehicle GROUP BY vehicle_id";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String amount = resultSet.getString("COUNT(vehicle_id)");
            return amount;
        } else {
         return "0";
        }
    }

    public static boolean updateQty(String vehicleId, int orderQty) throws SQLException {
        String sql = "UPDATE Vehicle SET qty = qty - ? WHERE vehicle_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,orderQty);
        pstm.setObject(2,vehicleId);
        return pstm.executeUpdate() > 0;
    }

    public static List<String> getVehicleId() throws SQLException {
        List<String> vehiId = new ArrayList<>();
        String sql = "SELECT vehicle_id FROM Vehicle";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            vehiId.add(resultSet.getString(1));
        }
        return vehiId;
    }
}
