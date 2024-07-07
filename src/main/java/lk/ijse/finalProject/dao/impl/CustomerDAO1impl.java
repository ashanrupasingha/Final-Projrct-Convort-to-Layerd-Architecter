package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.dao.CustomerDAO1;
import lk.ijse.finalProject.dao.SQLUtil;
import lk.ijse.finalProject.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO1impl implements CustomerDAO1 {
    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer searchById(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_id FROM Customer");
        List<String> idList = new ArrayList<>();
        while (resultSet.next()){
            idList.add(resultSet.getString("customer_id"));
        }
        return idList;
    }
}
