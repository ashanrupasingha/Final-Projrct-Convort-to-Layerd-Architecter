package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.dao.SQLUtil;
import lk.ijse.finalProject.dao.ServicePartDAO;
import lk.ijse.finalProject.model.PartsServiceDetail;

import java.sql.SQLException;
import java.util.List;

public class ServicePartDAOimpl implements ServicePartDAO {
    @Override
    public boolean update(PartsServiceDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(PartsServiceDetail dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Part_service_detail VALUES (?,?)",dto.getServiceId(),dto.getPartId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PartsServiceDetail searchById(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<PartsServiceDetail> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
