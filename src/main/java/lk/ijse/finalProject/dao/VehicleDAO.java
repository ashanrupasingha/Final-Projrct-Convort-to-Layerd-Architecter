package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.model.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDAO extends CrudDAO<Vehicle>{
    public boolean updateQty(String vehicleCode, int qty) throws SQLException, ClassNotFoundException;
    public List<String> getVehicleId() throws SQLException, ClassNotFoundException;
}
