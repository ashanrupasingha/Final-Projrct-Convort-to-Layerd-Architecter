package lk.ijse.finalProject.model.tm;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class orderTm {
private String  order_id;
private String  order_discription;
private String  customer_id;
private int     order_qty;
private String  vehicle_id;
}
