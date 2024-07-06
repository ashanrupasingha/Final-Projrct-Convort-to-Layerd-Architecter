package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.dao.impl.OrderDAO;
import lk.ijse.finalProject.model.Order;
import lk.ijse.finalProject.model.Employee;
import lk.ijse.finalProject.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOimpl implements OrderDAO {
    @Override
    public boolean update(Order orderDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Vehicle_order SET order_discription = ?, customer_id = ?, order_qty = ?, vehicle_id = ? WHERE order_id = ?",orderDTO.getOrder_discription(),orderDTO.getCustomer_id(),orderDTO.getOrder_qty(),orderDTO.getVehicle_id(),orderDTO.getOrder_id());
    }

    @Override
    public boolean save(Order orderDTO) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("INSERT INTO Vehicle_order VALUES(?,?,?,?,?)",orderDTO.getOrder_id(),orderDTO.getOrder_discription(),orderDTO.getCustomer_id(),orderDTO.getOrder_qty(),orderDTO.getVehicle_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM Vehicle_order WHERE order_id = ?",id);
    }

    @Override
    public Order searchById(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("SELECT * FROM  Vehicle_order WHERE order_id = ?",newValue +"");
        rst.next();
        Order orderDTO=new Order(newValue + "",rst.getString("order_discription"),rst.getString("customer_id"),rst.getInt("order_qty"),rst.getString("vehicle_id"));
        return orderDTO;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Vehicle_order");
        ArrayList<Order> orders = new ArrayList<>();
        while (rst.next()) {
           orders.add(new Order(rst.getString("order_id"),rst.getString("order_discription"),rst.getString("customer_id"),rst.getInt("order_qty"),rst.getString("vehicle_id")));
        }
        return orders;
    }
}

