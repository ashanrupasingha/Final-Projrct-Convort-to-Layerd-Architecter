package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.VehicleDetailDAO;
import lk.ijse.finalProject.dto.VehicleDetailsDTO;
import lk.ijse.finalProject.model.VehicleDetails;

import java.sql.SQLException;
import java.util.List;

public class VehicleDetailBOImpl implements VehicleDetailBO{
    VehicleDetailDAO vehicleDetailDAO = (VehicleDetailDAO) DAOFactory.getObject().getDao(DAOFactory.DAOType.VehicleDetails);
    @Override
    public boolean updateVehicleDetail(VehicleDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveVehicleDetail(VehicleDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return vehicleDetailDAO.save(new VehicleDetails(dto.getService_id(),dto.getCustomer_vehicle_id()));
    }

    @Override
    public boolean deleteVehicleDetail(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public VehicleDetailsDTO VehicleDetailsDTOearchById(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentVehicleDetailId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<VehicleDetailsDTO> getAllVehicleDetail() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
