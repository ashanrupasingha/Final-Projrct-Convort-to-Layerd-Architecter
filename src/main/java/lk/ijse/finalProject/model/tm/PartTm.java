package lk.ijse.finalProject.model.tm;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class PartTm {


    private String Part_id;
    private String Part_name;
    private int Part_qty;
    private double Price;
    private String Supplier_id;


}