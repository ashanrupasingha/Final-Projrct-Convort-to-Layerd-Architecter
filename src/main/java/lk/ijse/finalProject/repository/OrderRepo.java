package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {
    public static boolean save(Order order) throws SQLException {
        String sql = "INSERT INTO Vehicle_order VALUES(?,?,?,?,?)";

        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,order.getOrder_id());
        pstm.setObject(2,order.getOrder_discription());
        pstm.setObject(3,order.getCustomer_id());
        pstm.setObject(4,order.getOrder_qty());
        pstm.setObject(5,order.getVehicle_id());


        return pstm.executeUpdate() > 0;
    }
    public static Order searchById(String id) throws SQLException {
        String sql = "SELECT * FROM  Vehicle_order WHERE order_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String orderId = rs.getString(1);
            String orderdiscription = rs.getString(2);
            String customerid = rs.getString(3);
           int qty = Integer.parseInt(String.valueOf(rs.getInt(4)));
            String Vehicleid = rs.getString(5);


            Order order = new Order(orderId,orderdiscription,customerid,qty,Vehicleid);

            return order;

        }
        return null;

    }
    public static boolean update(Order order) throws SQLException {
        String sql = "UPDATE Vehicle_order SET order_discription = ?, customer_id = ?, order_qty = ?, vehicle_id = ? WHERE order_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, order.getOrder_discription());
        pstm.setObject(2, order.getCustomer_id());
        pstm.setObject(3, order.getOrder_qty());
        pstm.setObject(4, order.getVehicle_id());
        pstm.setObject(5, order.getOrder_id());
        return pstm.executeUpdate() > 0;
    }


    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Vehicle_order WHERE order_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Order> getAll() throws SQLException {
        String sql = "SELECT * FROM Vehicle_order";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Order> OrderList = new ArrayList<>();

        while (resultSet.next()) {
            String orderid = resultSet.getString(1);
            String orderdiscription = resultSet.getString(2);
            String customerid = resultSet.getString(3);
            int qty = Integer.parseInt(resultSet.getString(4));
            String vehicleid=resultSet.getString(5);

            Order order = new Order(orderid,orderdiscription,customerid,qty,vehicleid);
            OrderList.add(order);
        }
        return OrderList;
    }




    public static String getCarAmount() throws SQLException {
        String sql ="SELECT COUNT(*) Order_count FROM Vehicle_order";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String amount = resultSet.getString("Order_count");
            return amount;
        } else {
            return "0";
        }
    }
}
