package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBo extends SuperBo {
    boolean updateCustomer(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean saveCustomer(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    EmployeeDTO searchById(String newValue) throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;

}