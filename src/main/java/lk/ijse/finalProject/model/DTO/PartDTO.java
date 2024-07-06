package lk.ijse.finalProject.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PartDTO {
    private String part_id;
    private String part_name;
    private int part_qyt;
    private double Price;
    private String supplier_id;

}
