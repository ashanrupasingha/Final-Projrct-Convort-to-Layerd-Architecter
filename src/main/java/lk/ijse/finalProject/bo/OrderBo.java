package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dto.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo  {
    boolean updateCustomer(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    OrderDTO searchById(String newValue) throws SQLException, ClassNotFoundException;
    public List<OrderDTO> getAll() throws SQLException, ClassNotFoundException;
}
