package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dto.VehicleDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDetailBO extends SuperBo {
    public boolean updateVehicleDetail(VehicleDetailsDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean saveVehicleDetail(VehicleDetailsDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean deleteVehicleDetail(String id) throws SQLException, ClassNotFoundException ;

    public VehicleDetailsDTO VehicleDetailsDTOearchById(String newValue) throws SQLException, ClassNotFoundException ;

    public String getCurrentVehicleDetailId() throws SQLException, ClassNotFoundException ;

    public List<VehicleDetailsDTO> getAllVehicleDetail() throws SQLException, ClassNotFoundException ;
}
