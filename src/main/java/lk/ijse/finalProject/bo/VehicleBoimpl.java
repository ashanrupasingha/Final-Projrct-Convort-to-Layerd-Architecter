package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.VehicleDAO;

import java.sql.SQLException;

public class VehicleBoimpl implements VehicleBO {
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getObject().getDao(DAOFactory.DAOType.Vehicle);
    @Override
    public boolean updateAfterServicedQty(String vehicleCode, int qty) throws SQLException, ClassNotFoundException {
        return vehicleDAO.updateQty(vehicleCode,qty);
    }
}
