package lk.ijse.finalProject.controller.impl;

import lk.ijse.finalProject.model.Customer;
import lk.ijse.finalProject.model.CustomerVehicle;
import lk.ijse.finalProject.model.DTO.CustomerDTO;
import lk.ijse.finalProject.model.DTO.CustomerVehicleDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerVehicleBO {

    boolean updateCustomer(CustomerVehicle customervehicle) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerVehicle customervehicle) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public CustomerVehicleDTO searchById(String newValue) throws SQLException, ClassNotFoundException;
    public List<CustomerVehicleDTO> getAll() throws SQLException, ClassNotFoundException;
    public String getCurrentId() throws SQLException, ClassNotFoundException;

}
