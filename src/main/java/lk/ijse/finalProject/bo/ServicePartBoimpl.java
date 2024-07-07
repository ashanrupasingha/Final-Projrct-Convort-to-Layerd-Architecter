package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.ServicePartDAO;
import lk.ijse.finalProject.dto.PartsServiceDetailDTO;
import lk.ijse.finalProject.model.PartsServiceDetail;

import java.sql.SQLException;
import java.util.List;

public class ServicePartBoimpl implements ServicePartBO {
    ServicePartDAO servicePartDAO = (ServicePartDAO) DAOFactory.getObject().getDao(DAOFactory.DAOType.PartsServiceDetail);
    @Override
    public boolean updateServicePart(PartsServiceDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveServicePart(PartsServiceDetailDTO dto) throws SQLException, ClassNotFoundException {
        return servicePartDAO.save(new PartsServiceDetail(dto.getServiceId(),dto.getPartId()));
    }

    @Override
    public boolean deleteServicePart(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PartsServiceDetailDTO ServicePartsSearchById(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentServicePartId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<PartsServiceDetailDTO> getAllServicePart() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
