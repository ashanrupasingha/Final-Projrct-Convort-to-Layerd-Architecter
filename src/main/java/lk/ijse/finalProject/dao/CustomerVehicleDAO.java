package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.model.CustomerVehicle;

import java.sql.SQLException;
import java.util.List;

public interface CustomerVehicleDAO extends CrudDAO<CustomerVehicle>{
    public String getCustomerId(String cusVehiId) throws SQLException, ClassNotFoundException;
    public  List<String> getIds() throws SQLException, ClassNotFoundException;
    public  List<String> getName() throws SQLException, ClassNotFoundException;

}
