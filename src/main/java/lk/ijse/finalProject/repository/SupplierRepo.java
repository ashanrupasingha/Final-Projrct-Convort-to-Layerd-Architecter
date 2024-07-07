package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.model.Supplier;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierRepo {
    private String SupplierId;
    private String SupplierName;
    private String SupplierTEL;
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT supplier_id FROM Supplier ORDER BY supplier_id DESC LIMIT 1";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String supplierid = resultSet.getString(1);
            return supplierid;
        }
        return null;
    }

    public static List<String > getSupplierId() throws SQLException {
       String sql = "SELECT * FROM Supplier";
       PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> supId = new ArrayList<>();
        while (resultSet.next()){
            String id = resultSet.getString(1);
            supId.add(id);
        }
        return supId;
    }
    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE Supplier SET supplier_id = ?, supplier_name = ?,supplier_contact_number = ? WHERE supplier_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,supplier.getSupplier_id());
        pstm.setObject(2,supplier.getSupplier_name());
        pstm.setObject(3,supplier.getSupplier_contact_number());
        pstm.setObject(4,supplier.getSupplier_id());


        return pstm.executeUpdate() > 0;
    }
    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Supplier WHERE supplier_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()) {
            String supplier_id = resultSet.getString(1);
            String supplier_name = resultSet.getString(2);
            String service_tel = resultSet.getString(3);


            Supplier supplier = new Supplier(supplier_id,supplier_name,service_tel);
            supplierList.add(supplier);
        }
        return supplierList;
    }


    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO Supplier VALUES(?,?,?)";

        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplier.getSupplier_id());
        pstm.setObject(2, supplier.getSupplier_name());
        pstm.setObject(3, supplier.getSupplier_contact_number());


        return pstm.executeUpdate() > 0;
    }

    public static Supplier searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Supplier WHERE supplier_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String supplier_id = rs.getString(1);
            String suppliername = rs.getString(2);
            String suppliertel = rs.getString(3);



            Supplier supplier = new Supplier(supplier_id,suppliername,suppliertel);

            return supplier;

        }
        return null;

    }
}
