package lk.ijse.finalProject.controller.impl;

import lk.ijse.finalProject.dao.SaleDAOimpl;
import lk.ijse.finalProject.dao.ServiceDAOimpl;
import lk.ijse.finalProject.dao.impl.SaleDAO;
import lk.ijse.finalProject.dao.impl.ServiceDAO;
import lk.ijse.finalProject.model.DTO.ServiceDTO;
import lk.ijse.finalProject.model.DTO.VehicleDTO;
import lk.ijse.finalProject.model.Service;
import lk.ijse.finalProject.model.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceBoimpl implements ServiceBO {
    static ServiceDAO serviceDAO = new ServiceDAOimpl();
    @Override
    public boolean updateCustomer(ServiceDTO service) throws SQLException, ClassNotFoundException {
        return serviceDAO.update(new Service(service.getService_id(),service.getService_package(),service.getService_amount()));
    }

    @Override
    public boolean saveCustomer(ServiceDTO service) throws SQLException, ClassNotFoundException {
        return serviceDAO.save(new Service(service.getService_id(),service.getService_package(),service.getService_amount()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return serviceDAO.delete(id);
    }

    @Override
    public ServiceDTO searchById(String newValue) throws SQLException, ClassNotFoundException {
        Service service = serviceDAO.searchById(newValue);
        return new ServiceDTO(service.getService_id(),service.getService_package(),service.getService_amount());
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return serviceDAO.getCurrentId();
    }

    @Override
    public List<ServiceDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Service> vehicles = serviceDAO.getAll();
        List<ServiceDTO> serviceDTOS = new ArrayList<>();
        for (Service service : vehicles) {
            serviceDTOS.add(new ServiceDTO(service.getService_id(),service.getService_package(),service.getService_amount()));
        }
        return serviceDTOS;
    }
}
