package lk.ijse.finalProject.bo;

import java.sql.SQLException;
import java.util.List;

public interface VehicleBO extends SuperBo{
    public boolean updateAfterServicedQty(String vehicleCode, int qty) throws SQLException, ClassNotFoundException;
    public List<String> getVehicleId() throws SQLException, ClassNotFoundException;
}
