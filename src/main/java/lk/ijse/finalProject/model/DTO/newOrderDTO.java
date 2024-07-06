package lk.ijse.finalProject.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class newOrderDTO extends OrderDTO {
    private String orderId;
    private String customerId;
    private Date date;
}
