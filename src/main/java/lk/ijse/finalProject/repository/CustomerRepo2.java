package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo2 {
   /* public static String getCurrentId() throws SQLException {

        String sql = "SELECT Customer_id FROM Customer ORDER BY Customer_id DESC LIMIT 1";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String customerid = resultSet.getString(1);
            return customerid;
        }
        return null;
    }*/
  /*  public static boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer VALUES(?,?,?,?)";

        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,customer.getCustomer_id());
        pstm.setObject(2,customer.getName());
        pstm.setObject(3,customer.getAddress());
        pstm.setObject(4,customer.getTelephone());

        return pstm.executeUpdate() > 0;
    }*/
   /* public static Customer searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE Customer_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String cus_id = rs.getString(1);
            String Name = rs.getString(2);
            String address = rs.getString(3);
            String Telephone = rs.getString(4);


            Customer customer = new Customer(cus_id,Name,address,Telephone);

            return customer;

        }
        return null;

    }*/
 /*   public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET customer_id = ?, name = ?,address = ?,telephone = ?  WHERE Customer_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,customer.getCustomer_id());
        pstm.setObject(2,customer.getName());
        pstm.setObject(3,customer.getAddress());
        pstm.setObject(4,customer.getTelephone());
        pstm.setObject(5,customer.getCustomer_id());

        return pstm.executeUpdate() > 0;



    }*/

   /* public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Customer WHERE Customer_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }*/
    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM Customer";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String Name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String Telephone = resultSet.getString(4);

            Customer customer = new Customer(cus_id,Name,address,Telephone);
            cusList.add(customer);
        }
        return cusList;
    }


}
