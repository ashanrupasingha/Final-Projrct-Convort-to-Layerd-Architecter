package lk.ijse.finalProject.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class ServiceTm {
    private String service_id;
    private String service_package;
    private double service_amount;

}
