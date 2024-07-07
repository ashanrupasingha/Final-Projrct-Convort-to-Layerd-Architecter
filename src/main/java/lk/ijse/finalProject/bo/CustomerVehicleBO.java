package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dto.CustomerVehicleDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerVehicleBO {

    boolean updateCustomer(CustomerVehicleDTO customervehicle) throws SQLException, ClassNotFoundException;
    boolean saveCustomervehicle(CustomerVehicleDTO customervehicle) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public CustomerVehicleDTO searchById(String newValue) throws SQLException, ClassNotFoundException;
    public List<CustomerVehicleDTO> getAll() throws SQLException, ClassNotFoundException;
    public String getCurrentId() throws SQLException, ClassNotFoundException;

    public String getCustomerId(String cusVehiId) throws SQLException, ClassNotFoundException;

}
