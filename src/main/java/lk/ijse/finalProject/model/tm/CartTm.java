package lk.ijse.finalProject.model.tm;



import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTm {
    private String servicePackage;
    private String cusId;
    private String part;
    private int qty;
    private double total;
    private JFXButton btnRemove;



}
