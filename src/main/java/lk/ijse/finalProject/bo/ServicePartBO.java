package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dto.PartsServiceDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface ServicePartBO extends SuperBo{
    public boolean updateServicePart(PartsServiceDetailDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean saveServicePart(PartsServiceDetailDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean deleteServicePart(String id) throws SQLException, ClassNotFoundException ;

    public PartsServiceDetailDTO ServicePartsSearchById(String newValue) throws SQLException, ClassNotFoundException ;

    public String getCurrentServicePartId() throws SQLException, ClassNotFoundException ;

    public List<PartsServiceDetailDTO> getAllServicePart() throws SQLException, ClassNotFoundException ;
}
