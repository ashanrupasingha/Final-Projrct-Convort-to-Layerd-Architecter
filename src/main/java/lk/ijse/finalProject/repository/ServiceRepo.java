package lk.ijse.finalProject.repository;
import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.Customer;
import lk.ijse.finalProject.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepo {
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT service_id FROM Service ORDER BY service_id DESC LIMIT 1";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String serviceid = resultSet.getString(1);
            return serviceid;
        }
        return null;
    }
    public static boolean save(Service service) throws SQLException {
        String sql = "INSERT INTO Service VALUES(?,?,?)";

        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,service.getService_id());
        pstm.setObject(2,service.getService_package());
        pstm.setObject(3,service.getService_amount());


        return pstm.executeUpdate() > 0;
    }
    public static Service searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Service WHERE service_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String service_id = rs.getString(1);
            String service_package = rs.getString(2);
            double service_amount = rs.getDouble(3);



           Service service = new Service(service_id,service_package,service_amount);

            return service;

        }
        return null;

    }
    public static boolean update(Service service) throws SQLException {
        String sql = "UPDATE Service SET service_id = ?, service_package = ?,service_amount = ? WHERE service_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,service.getService_id());
        pstm.setObject(2,service.getService_package());
        pstm.setObject(3,service.getService_amount());
        pstm.setObject(4,service.getService_id());


        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Service WHERE service_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Service> getAll() throws SQLException {
        String sql = "SELECT * FROM Service";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Service> serviceList = new ArrayList<>();

        while (resultSet.next()) {
            String service_id = resultSet.getString(1);
            String service_package = resultSet.getString(2);
            double service_amount = resultSet.getDouble(3);


            Service service = new Service(service_id,service_package,service_amount);
            serviceList.add(service);
        }
        return serviceList;
    }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT service_id FROM Service";
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
        String sql = "SELECT service_package FROM Service";
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

    public static String  getPackageId(String packageName) throws SQLException {
        String sql = "SELECT service_id FROM Service WHERE service_package = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,packageName);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
           return resultSet.getString(1);
        }
        return null;
    }

//    public static String  getavailableId(String currentId) {
//        if (currentId != null){
//            String[] split = currentId.split("S00")
//        }
//    }
}
