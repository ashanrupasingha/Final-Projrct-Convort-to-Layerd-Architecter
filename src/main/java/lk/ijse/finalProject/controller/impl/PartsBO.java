package lk.ijse.finalProject.controller.impl;

import lk.ijse.finalProject.model.DTO.PartDTO;

import java.sql.SQLException;
import java.util.List;

public interface PartsBO {
    boolean updateCustomer(PartDTO partDTO) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(PartDTO partDTO) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    PartDTO searchById(String newValue) throws SQLException, ClassNotFoundException;
    public List<PartDTO> getAll() throws SQLException, ClassNotFoundException;
    public String getCurrentId() throws SQLException, ClassNotFoundException;
}
