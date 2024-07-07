package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.dao.SQLUtil;
import lk.ijse.finalProject.dao.SaleDAO;
import lk.ijse.finalProject.model.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDAOimpl implements SaleDAO {

    @Override
    public boolean update(Vehicle customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Vehicle SET vehicle_id = ?, vehicle_model= ?,vehicle_number = ?,supplier_id = ?,qty = ? WHERE vehicle_id = ?",customer.getVehicle_id(),customer.getVehicle_model(),customer.getVehicle_number(),customer.getSupplier_id(),customer.getQty());
    }

    @Override
    public boolean save(Vehicle dto) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("INSERT INTO Vehicle VALUES(?,?,?,?,?)",dto.getVehicle_id(),dto.getVehicle_model(),dto.getVehicle_number(),dto.getSupplier_id(),dto.getQty());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM Vehicle WHERE Vehicle_id = ?",id);
    }

    @Override
    public Vehicle searchById(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("SELECT * FROM  Vehicle WHERE vehicle_id = ?",newValue + "" );
        rst.next();
        Vehicle vehicle =new Vehicle(newValue + "" ,rst.getString("vehicle_model"),rst.getString("vehicle_number"),rst.getString("supplier_id "),rst.getInt("qty"));
        return vehicle;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT vehicle_id FROM Vehicle ORDER BY vehicle_id DESC LIMIT 1");
        rst.next();
        return rst.getString("vehicle_id");
    }

    @Override
    public List<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Vehicle");
        ArrayList<Vehicle> parts = new ArrayList<>();
        while (rst.next()) {
            parts.add(new Vehicle(rst.getString("vehicle_id"),rst.getString("vehicle_model"),rst.getString("vehicle_number"),rst.getString("supplier_id"),rst.getInt("qty")));
        }
        return parts;
    }
}
