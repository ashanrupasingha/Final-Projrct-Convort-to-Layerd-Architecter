package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.model.CustomerVehicle;

import java.sql.SQLException;

public interface CustomerVehicleDAO extends CrudDAO<CustomerVehicle>{
    public String getCustomerId(String cusVehiId) throws SQLException, ClassNotFoundException;
}
