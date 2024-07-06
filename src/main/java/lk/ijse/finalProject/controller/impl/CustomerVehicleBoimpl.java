package lk.ijse.finalProject.controller.impl;

import lk.ijse.finalProject.dao.*;
import lk.ijse.finalProject.dao.impl.CustomerVehicleDAO;
import lk.ijse.finalProject.model.CustomerVehicle;
import lk.ijse.finalProject.model.DTO.CustomerVehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerVehicleBoimpl implements  CustomerVehicleBO{
    static CustomerVehicleDAO customervehicleDAO=new CustomerVehicleDAOimpl();
    @Override
    public boolean updateCustomer(CustomerVehicle customervehicle) throws SQLException, ClassNotFoundException {
     return customervehicleDAO.update(customervehicle);
    }
    public  boolean saveCustomer(CustomerVehicle customervehicle) throws SQLException, ClassNotFoundException {

        return customervehicleDAO.save(customervehicle);
    }
    public  boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {

        return customervehicleDAO.delete(id);
    }
    public CustomerVehicleDTO searchById(String newValue) throws SQLException, ClassNotFoundException {
        CustomerVehicle customervehicle = customervehicleDAO.searchById(newValue);
        return new CustomerVehicleDTO(customervehicle.getCustomervehicle_id(),customervehicle.getCustomer_id(),customervehicle.getVehicleNumber());
    }

    @Override
    public List<CustomerVehicleDTO> getAll() throws SQLException, ClassNotFoundException {
        List<CustomerVehicle> customerVehicles = customervehicleDAO.getAll();
        List<CustomerVehicleDTO> customerVehicleDTOS = new ArrayList<>();
        for(CustomerVehicle vehicle: customerVehicles){
            customerVehicleDTOS.add(new CustomerVehicleDTO(vehicle.getCustomervehicle_id(),vehicle.getCustomer_id(),vehicle.getVehicleNumber()));
        }
        return customerVehicleDTOS;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return customervehicleDAO.getCurrentId();
    }
}
