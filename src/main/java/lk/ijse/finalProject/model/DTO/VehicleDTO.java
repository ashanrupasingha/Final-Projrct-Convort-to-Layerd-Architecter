package lk.ijse.finalProject.model.DTO;

public class VehicleDTO {
    private String  Vehicle_id;
    private  String vehicle_model;
    private  String vehicle_number;
    private String supplier_id;
    private int qty;

    public VehicleDTO() {
    }

    public VehicleDTO(String vehicle_id, String vehicle_model, String vehicle_number, String supplier_id, int qty) {
        Vehicle_id = vehicle_id;
        this.vehicle_model = vehicle_model;
        this.vehicle_number = vehicle_number;
        this.supplier_id = supplier_id;
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getVehicle_id() {
        return Vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        Vehicle_id = vehicle_id;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }
}
