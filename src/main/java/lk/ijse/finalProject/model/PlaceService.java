package lk.ijse.finalProject.model;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.repository.CustomerVehicleRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceService {
    private VehicleDetails vehicleDetails;
    private PartsServiceDetail partsServiceDetail;
    private int qty;
}
