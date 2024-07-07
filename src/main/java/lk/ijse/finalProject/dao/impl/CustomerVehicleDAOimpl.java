package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.dao.SQLUtil;
import lk.ijse.finalProject.dao.CustomerVehicleDAO;
import lk.ijse.finalProject.model.CustomerVehicle;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerVehicleDAOimpl implements CustomerVehicleDAO {

    @Override
    public boolean update(CustomerVehicle customervehicle) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer_vehicle SET customer_id = ?,vehicle_number = ? WHERE customer_vehicle_id = ?",customervehicle.getCustomer_id(),customervehicle.getVehicleNumber(),customervehicle.getCustomervehicle_id());
    }


    public boolean save(CustomerVehicle customerVehicle) throws SQLException, ClassNotFoundException {
      return SQLUtil.execute("INSERT INTO Customer_vehicle VALUES(?,?,?)",customerVehicle.getCustomervehicle_id(),customerVehicle.getCustomer_id(),customerVehicle.getVehicleNumber());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer_vehicle WHERE customer_vehicle_id = ?",id);
    }

    @Override
    public CustomerVehicle searchById(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("SELECT * FROM Customer_vehicle WHERE customer_vehicle_id = ?",newValue +"");
        rst.next();
       CustomerVehicle customervehicle = new CustomerVehicle(newValue + "",rst.getString("customer_id"),rst.getString("vehicle_number"));
        return customervehicle;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Customer_vehicle_id FROM Customer_vehicle ORDER BY customer_vehicle_id DESC LIMIT 1");
        rst.next();
        return rst.getString("customervehicle_id");
    }

    @Override
    public List<CustomerVehicle> getAll() throws SQLException, ClassNotFoundException {
         ResultSet rst= SQLUtil.execute("SELECT * FROM Customer_vehicle");
        ArrayList<CustomerVehicle> customerVehicles =new ArrayList<>();
        while (rst.next()){
          customerVehicles.add(new CustomerVehicle(rst.getString("Customer_vehicle_id"),rst.getString("customer_id"),rst.getString("vehicle_number")));
        }
        return customerVehicles;
    }


    @Override
    public String getCustomerId(String cusVehiId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT customer_id FROM Customer_vehicle WHERE customer_vehicle_id = ?",cusVehiId);
        if (rst.next()){
            return rst.getString("customer_id");
        }
        return null;
    }
}
