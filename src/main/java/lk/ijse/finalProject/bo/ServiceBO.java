package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dto.ServiceDTO;

import java.sql.SQLException;
import java.util.List;

public interface ServiceBO extends SuperBo{

    boolean updateCustomer(ServiceDTO service) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(ServiceDTO service) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public ServiceDTO searchById(String newValue) throws SQLException, ClassNotFoundException ;
    public String getCurrentId() throws SQLException, ClassNotFoundException;
    public List<ServiceDTO> getAll() throws SQLException, ClassNotFoundException;
}
