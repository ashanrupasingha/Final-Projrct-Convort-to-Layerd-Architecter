package lk.ijse.finalProject.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private String  order_id;
    private String  order_discription;
    private String  customer_id;
    private int     order_qty;
    private String  vehicle_id;
}
