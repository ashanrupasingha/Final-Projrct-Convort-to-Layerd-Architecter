package lk.ijse.finalProject.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceServiceDTO {
    private VehicleDetailsDTO vehicleDetails;
    private PartsServiceDetailDTO partsServiceDetail;
    private int qty;
}
