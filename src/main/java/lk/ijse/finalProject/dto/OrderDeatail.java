package lk.ijse.finalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OrderDeatail {

    public OrderDeatail(String orderId, String code, int qty, double unitPrice) {
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class OrderDetail {
        private String orderId;
        private String itemCode;
        private int qty;
        private double unitPrice;
    }

}
