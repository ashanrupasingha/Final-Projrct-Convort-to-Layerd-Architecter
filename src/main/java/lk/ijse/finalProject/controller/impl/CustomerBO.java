package lk.ijse.finalProject.controller.impl;

import lk.ijse.finalProject.dao.CustomerDAOimpl;
import lk.ijse.finalProject.model.Customer;
import lk.ijse.finalProject.model.DTO.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO {

    boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public CustomerDTO searchById(String newValue) throws SQLException, ClassNotFoundException ;
    public String getCurrentId() throws SQLException, ClassNotFoundException;
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

}