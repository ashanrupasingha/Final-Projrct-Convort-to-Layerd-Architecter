package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.Part;
import lk.ijse.finalProject.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleRepo {

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT vehicle_id FROM Vehicle ORDER BY vehicle_id DESC LIMIT 1";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String vehicleid = resultSet.getString(1);
            return vehicleid;
        }
        return null;
    }
    public static boolean save(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO Vehicle VALUES(?,?,?,?,?)";

        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,vehicle.getVehicle_id());
        pstm.setObject(2,vehicle.getVehicle_model());
        pstm.setObject(3,vehicle.getVehicle_number());
        pstm.setObject(4,vehicle.getSupplier_id());
        pstm.setObject(5,vehicle.getQty());


        return pstm.executeUpdate() > 0;
    }
    public static Vehicle searchById(String id) throws SQLException {
        String sql = "SELECT * FROM  Vehicle WHERE vehicle_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String vehiId = rs.getString(1);
            String model = rs.getString(2);
            String vehiNum = rs.getString(3);
            String Supplier_id = rs.getString(4);
            int qty = Integer.parseInt(rs.getString(5));


            Vehicle vehicle = new Vehicle(vehiId,model,vehiNum,Supplier_id,qty);

            return vehicle;

        }
        return null;

    }
    public static boolean update(Vehicle vehicle) throws SQLException {
        String sql = "UPDATE Vehicle SET vehicle_id = ?, vehicle_model= ?,vehicle_number = ?,supplier_id = ?,qty = ? WHERE vehicle_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,vehicle.getVehicle_id());
        pstm.setObject(2,vehicle.getVehicle_model());
        pstm.setObject(3,vehicle.getVehicle_number());
        pstm.setObject(4,vehicle.getSupplier_id());
        pstm.setObject(5,vehicle.getQty());
        pstm.setObject(6,vehicle.getVehicle_id());
        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Vehicle WHERE Vehicle_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Vehicle> getAll() throws SQLException {
        String sql = "SELECT * FROM Vehicle";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Vehicle> VehicleList = new ArrayList<>();

        while (resultSet.next()) {
            String vehicle_id = resultSet.getString(1);
            String vehicle_model = resultSet.getString(2);
            String vehicle_number = resultSet.getString(3);
            String supplier_id = resultSet.getString(4);
            int qty = Integer.parseInt(resultSet.getString(5));

            Vehicle vehicle = new Vehicle(vehicle_id,vehicle_model,vehicle_number,supplier_id,qty);
            VehicleList.add(vehicle);
        }
        return VehicleList;
    }
}
