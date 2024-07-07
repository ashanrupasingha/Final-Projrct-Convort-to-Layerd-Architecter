package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.dao.SQLUtil;
import lk.ijse.finalProject.dao.VehicleDetailDAO;
import lk.ijse.finalProject.model.VehicleDetails;

import java.sql.SQLException;
import java.util.List;

public class VehicleDetailDAOImpl implements VehicleDetailDAO {
    @Override
    public boolean update(VehicleDetails dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(VehicleDetails dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO vehicle_detail VALUES (?,?)",dto.getService_id(),dto.getCustomer_vehicle_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public VehicleDetails searchById(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<VehicleDetails> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
