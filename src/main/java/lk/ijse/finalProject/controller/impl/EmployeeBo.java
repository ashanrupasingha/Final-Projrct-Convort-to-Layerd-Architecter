package lk.ijse.finalProject.controller.impl;

import lk.ijse.finalProject.model.DTO.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBo {
    boolean updateCustomer(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean saveCustomer(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    EmployeeDTO searchById(String newValue) throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;

}