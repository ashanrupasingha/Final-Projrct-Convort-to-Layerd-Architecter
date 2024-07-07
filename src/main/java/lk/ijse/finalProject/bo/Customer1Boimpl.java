package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dao.CustomerDAO;
import lk.ijse.finalProject.dao.CustomerDAO1;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.impl.CustomerDAO1impl;
import lk.ijse.finalProject.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public class Customer1Boimpl implements CustomerBo1{
    static CustomerDAO1 customerDAO1= new CustomerDAO1impl();
    @Override
    public boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public CustomerDTO searchById(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
      return customerDAO1.getId();
    }
}
