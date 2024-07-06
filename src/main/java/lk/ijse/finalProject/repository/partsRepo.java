package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.Customer;
import lk.ijse.finalProject.model.Part;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class partsRepo {

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT part_id FROM Part ORDER BY part_id DESC LIMIT 1";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String partid = resultSet.getString(1);
            return partid;
        }
        return null;
    }
    public static boolean save(Part part) throws SQLException {
        String sql = "INSERT INTO Part VALUES(?,?,?,?,?)";

        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,part.getPart_id());
        pstm.setObject(2,part.getPart_name());
        pstm.setObject(3,part.getPart_qyt());
        pstm.setObject(4,part.getPrice());
        pstm.setObject(5,part.getSupplier_id());


        return pstm.executeUpdate() > 0;
    }
    public static Part searchById(String id) throws SQLException {
        String sql = "SELECT * FROM  Part WHERE part_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String part_id = rs.getString(1);
            String part_name = rs.getString(2);
            int part_qty = rs.getInt(3);
            double price = rs.getDouble(4);
            String Supplier_id = rs.getString(5);


            Part part = new Part(part_id,part_name,part_qty,price,Supplier_id);

            return part;

        }
        return null;

    }
    public static boolean update(Part part) throws SQLException {
        String sql = "UPDATE Part SET part_id = ?, part_name = ?,part_qty = ?,price = ? ,supplier_id = ? WHERE part_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,part.getPart_id());
        pstm.setObject(2,part.getPart_name());
        pstm.setObject(3,part.getPart_qyt());
        pstm.setObject(4,part.getPrice());
        pstm.setObject(5,part.getSupplier_id());
        pstm.setObject(6,part.getPart_id());


        return pstm.executeUpdate() > 0;



    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Part WHERE part_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Part> getAll() throws SQLException {
        String sql = "SELECT * FROM Part";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Part> partList = new ArrayList<>();

        while (resultSet.next()) {
            String part_id = resultSet.getString(1);
            String part_name = resultSet.getString(2);
            int part_qty = resultSet.getInt(3);
            double price = resultSet.getDouble(4);
            String Supplier_id = resultSet.getString(5);

            Part part = new Part(part_id,part_name,part_qty,price,Supplier_id);
            partList.add(part);
        }
        return partList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT part_id FROM Part";
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


    public static List<String> getNames() throws SQLException {
        List<String> nameList = new ArrayList<>();
        String sql = "SELECT part_name FROM Part";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            nameList.add(resultSet.getString("part_name"));
        }
        return nameList;
    }
}
