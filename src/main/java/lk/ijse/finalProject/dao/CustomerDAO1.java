package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO1 extends CrudDAO<Customer>{
    public  List<String> getId() throws SQLException, ClassNotFoundException;
}
