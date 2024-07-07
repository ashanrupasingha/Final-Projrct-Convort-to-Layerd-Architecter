package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.dao.SQLUtil;
import lk.ijse.finalProject.dao.CustomerDAO;
import lk.ijse.finalProject.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOimpl implements CustomerDAO {

   public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET customer_id = ?, name = ?,address = ?,telephone = ?  WHERE Customer_id = ?",customer.getCustomer_id(),customer.getName(),customer.getAddress(),customer.getTelephone(),customer.getCustomer_id());

   }
    public  boolean save(Customer customer) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO Customer VALUES(?,?,?,?)",customer.getCustomer_id(),customer.getName(),customer.getAddress(),customer.getTelephone());
           }


    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("DELETE FROM Customer WHERE customer_id = ?",id);
    }

    public  Customer searchById(String newValue) throws SQLException, ClassNotFoundException {

        ResultSet rst =SQLUtil.execute("SELECT * FROM Customer WHERE customer_id = ?",newValue);
       rst.next();
        Customer customer =new Customer(newValue , rst.getString("name"), rst.getString("address"), rst.getString("telephone") );
        return customer;
    }

    public String getCurrentId() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT customer_id FROM Customer ORDER BY customer_id DESC LIMIT 1");
       rst.next();
       return rst.getString("customer_id");
    }
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customer = new ArrayList<>();
        while (rst.next()) {
            customer.add(new Customer(rst.getString("customer_id"), rst.getString("name"), rst.getString("address"), rst.getString("telephone")));
        }
return customer;
    }
}
