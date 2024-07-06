package lk.ijse.finalProject.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Part {
    private String part_id;
    private String part_name;
    private int part_qyt;
    private double Price;
    private String supplier_id;

}
