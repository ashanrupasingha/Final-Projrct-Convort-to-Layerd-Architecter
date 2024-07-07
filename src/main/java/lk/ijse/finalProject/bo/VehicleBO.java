package lk.ijse.finalProject.bo;

import java.sql.SQLException;

public interface VehicleBO {
    public boolean updateAfterServicedQty(String vehicleCode, int qty) throws SQLException, ClassNotFoundException;
}
