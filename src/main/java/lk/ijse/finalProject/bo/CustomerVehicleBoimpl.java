package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dao.CustomerVehicleDAO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.model.CustomerVehicle;
import lk.ijse.finalProject.dto.CustomerVehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerVehicleBoimpl implements  CustomerVehicleBO{
    static CustomerVehicleDAO customervehicleDAO= (CustomerVehicleDAO) DAOFactory.getObject().getDao(DAOFactory.DAOType.CustomerVehicle);
    @Override
    public boolean updateCustomer(CustomerVehicleDTO customervehicle) throws SQLException, ClassNotFoundException {
     return customervehicleDAO.update(new CustomerVehicle(customervehicle.getCustomervehicle_id(), customervehicle.getCustomer_id(),customervehicle.getVehicleNumber()));
    }
    public  boolean saveCustomervehicle(CustomerVehicleDTO customervehicle) throws SQLException, ClassNotFoundException {

        return customervehicleDAO.save(new CustomerVehicle(customervehicle.getCustomervehicle_id(),customervehicle.getCustomer_id(), customervehicle.getVehicleNumber()));
    }
    public  boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {

        return customervehicleDAO.delete(id);
    }
    public CustomerVehicleDTO searchById(String newValue) throws SQLException, ClassNotFoundException {
        CustomerVehicle customervehicle = customervehicleDAO.searchById(newValue);
        return new CustomerVehicleDTO(customervehicle.getCustomervehicle_id(),customervehicle.getCustomer_id(),customervehicle.getVehicleNumber());
    }

    @Override
    public List<CustomerVehicleDTO> getAll() throws SQLException, ClassNotFoundException {
        List<CustomerVehicle> customerVehicles = customervehicleDAO.getAll();
        List<CustomerVehicleDTO> customerVehicleDTOS = new ArrayList<>();
        for(CustomerVehicle vehicle: customerVehicles){
            customerVehicleDTOS.add(new CustomerVehicleDTO(vehicle.getCustomervehicle_id(),vehicle.getCustomer_id(),vehicle.getVehicleNumber()));
        }
        return customerVehicleDTOS;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return customervehicleDAO.getCurrentId();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return customervehicleDAO.getIds();
    }

    @Override
    public List<String> getName() throws SQLException, ClassNotFoundException {
        return customervehicleDAO.getName();
    }

    @Override
    public String getCustomerId(String cusVehiId) throws SQLException, ClassNotFoundException {
        return customervehicleDAO.getCustomerId(cusVehiId);
    }

}
