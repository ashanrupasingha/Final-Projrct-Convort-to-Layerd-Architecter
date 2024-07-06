package lk.ijse.finalProject.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
    private String  order_id;
    private String  order_discription;
    private String  customer_id;
    private int     order_qty;
    private String  vehicle_id;
}
