package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBo{
    boolean updateCustomer(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public SupplierDTO searchById(String newValue) throws SQLException, ClassNotFoundException ;
    public String getCurrentId() throws SQLException, ClassNotFoundException;
    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException;
}
