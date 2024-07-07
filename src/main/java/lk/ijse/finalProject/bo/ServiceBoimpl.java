package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.ServiceDAO;
import lk.ijse.finalProject.dto.ServiceDTO;
import lk.ijse.finalProject.model.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceBoimpl implements ServiceBO {
    static ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getObject().getDao(DAOFactory.DAOType.Service);
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

    @Override
    public List<String> getName() throws SQLException, ClassNotFoundException {
        return serviceDAO.getName();
    }
}
