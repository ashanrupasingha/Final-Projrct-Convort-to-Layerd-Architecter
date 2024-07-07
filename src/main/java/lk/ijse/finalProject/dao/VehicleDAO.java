package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.model.Vehicle;

import java.sql.SQLException;

public interface VehicleDAO extends CrudDAO<Vehicle>{
    public boolean updateQty(String vehicleCode, int qty) throws SQLException, ClassNotFoundException;
}
