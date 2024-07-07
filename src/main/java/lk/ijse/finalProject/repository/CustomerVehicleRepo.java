package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.model.CustomerVehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerVehicleRepo {
    public static String getCarAmount() throws SQLException {
        String sql ="SELECT COUNT(*) customer_count FROM Customer_vehicle";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String amount = resultSet.getString("customer_count");
            return amount;
        } else {
            return "0";
        }
    }
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT Customer_vehicle_id FROM Customer_vehicle ORDER BY customer_vehicle_id DESC LIMIT 1";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String serviceid = resultSet.getString(1);
            return serviceid;
        }
        return null;
    }
   /* public static boolean save(CustomerVehicle customerVehicle) throws SQLException {
        String sql = "INSERT INTO Customer_vehicle VALUES(?,?,?)";

        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,customerVehicle.getCustomervehicle_id());
        pstm.setObject(2,customerVehicle.getCustomer_id());
        pstm.setObject(3,customerVehicle.getVehicleNumber());


        return pstm.executeUpdate() > 0;
    }*/
    /*public static CustomerVehicle searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Customer_vehicle WHERE customer_vehicle_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String customervehicle_id = rs.getString(1);
            String customer_id = rs.getString(2);
            String vehicleNumber = rs.getString(3);



            CustomerVehicle customerVehicle = new CustomerVehicle(customervehicle_id,customer_id,vehicleNumber, rst.getString("vehicleNumber"));

            return customerVehicle;

        }
        return null;

    }*/
 /*   public static boolean update(CustomerVehicle customerVehicle) throws SQLException {
        String sql = "UPDATE Customer_vehicle SET customer_vehicle_id = ?, customer_id = ?,vehicle_number = ? WHERE customer_vehicle_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,customerVehicle.getCustomervehicle_id());
        pstm.setObject(2,customerVehicle.getCustomer_id());
        pstm.setObject(3,customerVehicle.getVehicleNumber());
        pstm.setObject(4,customerVehicle.getCustomervehicle_id());


        return pstm.executeUpdate() > 0;
    }
*/
   /* public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Customer_vehicle WHERE customer_vehicle_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }*/
   public static List<CustomerVehicle> getAll() throws SQLException {
       String sql = "SELECT * FROM Customer_vehicle";

       PreparedStatement pstm = Dbconnection.getInstance().getConnection()
               .prepareStatement(sql);

       ResultSet resultSet = pstm.executeQuery();

       List<CustomerVehicle> serviceList = new ArrayList<>();

       while (resultSet.next()) {
           String customervehicle_id = resultSet.getString(1);
           String cus_id = resultSet.getString(2);
           String  vehiclenumber = resultSet.getString(3);


           CustomerVehicle customerVehicle = new CustomerVehicle(customervehicle_id,cus_id,vehiclenumber);
           serviceList.add(customerVehicle);
       }
       return serviceList;
   }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT customer_vehicle_id FROM Customer_vehicle";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public static List<String> getName() throws SQLException {
        String sql = "SELECT customer_id  FROM Customer_vehicle";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

}
