package lk.ijse.finalProject.controller.impl;

import lk.ijse.finalProject.model.DTO.CustomerDTO;
import lk.ijse.finalProject.model.DTO.EmployeeDTO;
import lk.ijse.finalProject.model.DTO.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo  {
    boolean updateCustomer(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    OrderDTO searchById(String newValue) throws SQLException, ClassNotFoundException;
    public List<OrderDTO> getAll() throws SQLException, ClassNotFoundException;
}
