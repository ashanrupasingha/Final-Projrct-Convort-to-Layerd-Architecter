package lk.ijse.finalProject.model.tm;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data

public class SaleTm {
    private  String vehicle_id;
    private  String vehicle_model;
    private  String vehicle_Number;
    private  String supplier_id;
    private  int qty;
}
