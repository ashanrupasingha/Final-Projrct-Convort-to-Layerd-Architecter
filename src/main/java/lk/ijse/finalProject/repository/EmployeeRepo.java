package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT employee_id FROM Employee ORDER BY employee_id DESC LIMIT 1";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String employeeId = resultSet.getString(1);
            return employeeId;
        }
        return null;
    }
    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee VALUES(?,?,?,?)";

        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,employee.getEmployee_id());
        pstm.setObject(2,employee.getEmployee_name());
        pstm.setObject(3,employee.getAddress());
        pstm.setObject(4,employee.getTelephone());

        return pstm.executeUpdate() > 0;
    }
    public static Employee searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE employee_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String Employee_id = rs.getString(1);
            String Employee_name = rs.getString(2);
            String Employee_address = rs.getString(3);
            String Telephone = rs.getString(4);


           Employee employee = new Employee(Employee_id,Employee_name,Employee_address,Telephone);

            return employee ;

        }
        return null;

    }
    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET employee_id = ?, employee_name = ?,address = ?,telephone = ?  WHERE employee_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,employee.getEmployee_id());
        pstm.setObject(2,employee.getEmployee_name());
        pstm.setObject(3,employee.getAddress());
        pstm.setObject(4,employee.getTelephone());
        pstm.setObject(5,employee.getEmployee_id());


        return pstm.executeUpdate() > 0;



    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE employee_id = ?";
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";

        PreparedStatement pstm = Dbconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Employee> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String employee_id = resultSet.getString(1);
            String Name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String Telephone = resultSet.getString(4);

            Employee employee = new Employee(employee_id,Name,address,Telephone);
            cusList.add(employee);
        }
        return cusList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT customer_id FROM Customer";
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
