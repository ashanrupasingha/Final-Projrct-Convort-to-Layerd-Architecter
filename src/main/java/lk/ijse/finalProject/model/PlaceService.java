package lk.ijse.finalProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceService {
    private VehicleDetails vehicleDetails;
    private PartsServiceDetail partsServiceDetail;
    private int qty;
}
