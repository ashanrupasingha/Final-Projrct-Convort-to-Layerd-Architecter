package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo1 extends SuperBo{
    boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public CustomerDTO searchById(String newValue) throws SQLException, ClassNotFoundException ;
    public String getCurrentId() throws SQLException, ClassNotFoundException;
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    public  List<String> getId() throws SQLException, ClassNotFoundException;
}
