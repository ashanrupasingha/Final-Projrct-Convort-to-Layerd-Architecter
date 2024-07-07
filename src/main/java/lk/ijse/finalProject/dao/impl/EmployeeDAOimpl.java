package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.dao.SQLUtil;
import lk.ijse.finalProject.dao.EmployeeDAO;
import lk.ijse.finalProject.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOimpl implements EmployeeDAO {
    @Override
    public boolean update(Employee employeeDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employee SET employee_id = ?, employee_name = ?,address = ?,telephone = ?  WHERE employee_id = ?",employeeDTO.getEmployee_id(),employeeDTO.getEmployee_name(),employeeDTO.getAddress(),employeeDTO.getTelephone(),employeeDTO.getEmployee_id());
    }

    @Override
    public boolean save(Employee employeeDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee VALUES(?,?,?,?)",employeeDTO.getEmployee_id(),employeeDTO.getEmployee_name(),employeeDTO.getAddress(),employeeDTO.getTelephone());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE employee_id = ?",id);
    }

    @Override
    public Employee searchById(String newValue) throws SQLException, ClassNotFoundException {

        ResultSet rst =SQLUtil.execute("SELECT * FROM Employee WHERE employee_id = ?",newValue +"");
        rst.next();
        Employee employeeDTO =new Employee(newValue + "", rst.getString("employee_name"), rst.getString("address"), rst.getString("telephone"));
        return employeeDTO;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT employee_id FROM Employee ORDER BY employee_id DESC LIMIT 1");
        rst.next();
        return rst.getString("employee_id");
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee");
        ArrayList<Employee> employees = new ArrayList<>();
        while (rst.next()) {
           employees.add(new Employee(rst.getString("employee_id"), rst.getString("employee_name"), rst.getString("address"), rst.getString("telephone")));
        }
        return employees;
    }
}
