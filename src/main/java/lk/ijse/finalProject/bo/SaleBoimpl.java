package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.SaleDAO;
import lk.ijse.finalProject.dto.VehicleDTO;
import lk.ijse.finalProject.model.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleBoimpl implements SaleBo{
    static SaleDAO saleDAO = (SaleDAO) DAOFactory.getObject().getDao(DAOFactory.DAOType.Sale);

    @Override
    public boolean updateCustomer(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
        return saleDAO.update(new Vehicle(vehicle.getVehicle_id(),vehicle.getVehicle_model(),vehicle.getVehicle_number(),vehicle.getSupplier_id(),vehicle.getQty()));
    }

    @Override
    public boolean saveCustomer(VehicleDTO customer) throws SQLException, ClassNotFoundException {
        return saleDAO.save(new Vehicle(customer.getVehicle_id(),customer.getVehicle_model(),customer.getVehicle_number(),customer.getSupplier_id(),customer.getQty()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return saleDAO.delete(id);
    }

    @Override
    public VehicleDTO searchById(String newValue) throws SQLException, ClassNotFoundException {
        Vehicle vehicle = saleDAO.searchById(newValue);
        return new VehicleDTO(vehicle.getVehicle_id(),vehicle.getVehicle_model(),vehicle.getVehicle_number(),vehicle.getSupplier_id(),vehicle.getQty());
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return saleDAO.getCurrentId();
    }

    @Override
    public List<VehicleDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Vehicle> vehicles = saleDAO.getAll();
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleDTOS.add(new VehicleDTO(vehicle.getVehicle_id(),vehicle.getVehicle_model(),vehicle.getVehicle_number(),vehicle.getSupplier_id(),vehicle.getQty()));
        }
        return vehicleDTOS;
    }
}
