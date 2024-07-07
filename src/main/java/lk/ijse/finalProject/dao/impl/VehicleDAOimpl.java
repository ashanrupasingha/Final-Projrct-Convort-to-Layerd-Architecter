package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.dao.SQLUtil;
import lk.ijse.finalProject.dao.VehicleDAO;
import lk.ijse.finalProject.model.Vehicle;

import java.sql.SQLException;
import java.util.List;

public class VehicleDAOimpl implements VehicleDAO {

    public boolean updateVehicle(Vehicle dto) throws SQLException, ClassNotFoundException {
        return false;
    }


    public boolean saveVehicle(Vehicle dto) throws SQLException, ClassNotFoundException {
        return false;
    }


    public boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    public Vehicle VehiclesearchById(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public boolean update(Vehicle dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(Vehicle dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Vehicle searchById(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }


    public List<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean updateQty(String vehicleCode, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Vehicle SET qty = qty - ? WHERE vehicle_id =?",qty,vehicleCode);
    }
}
