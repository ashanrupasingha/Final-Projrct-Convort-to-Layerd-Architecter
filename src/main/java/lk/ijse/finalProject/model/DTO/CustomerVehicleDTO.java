package lk.ijse.finalProject.model.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerVehicleDTO {
    private String customervehicle_id;
    private String customer_id;
    private String vehicleNumber;
}
