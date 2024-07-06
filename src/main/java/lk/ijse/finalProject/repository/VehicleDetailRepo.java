package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.VehicleDetails;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleDetailRepo {
    public static boolean save(VehicleDetails vehicleDetails) throws SQLException {
        String sql = "INSERT INTO vehicle_detail VALUES (?,?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,vehicleDetails.getService_id());
        pstm.setObject(2,vehicleDetails.getCustomer_vehicle_id());
        int i = pstm.executeUpdate();
        System.out.println("i"+i);
        return i > 0;
    }
}
