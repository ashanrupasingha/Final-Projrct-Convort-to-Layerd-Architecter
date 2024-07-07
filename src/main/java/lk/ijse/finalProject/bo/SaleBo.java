package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dto.VehicleDTO;

import java.sql.SQLException;
import java.util.List;

public interface SaleBo extends SuperBo {

    boolean updateCustomer(VehicleDTO customer) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(VehicleDTO customer) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public VehicleDTO searchById(String newValue) throws SQLException, ClassNotFoundException ;
    public String getCurrentId() throws SQLException, ClassNotFoundException;
    public List<VehicleDTO> getAll() throws SQLException, ClassNotFoundException;
}
