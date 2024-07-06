package lk.ijse.finalProject.model.tm;

public class VehicleTm {
    private String id;
    private String model;
    private String vehicle_number;
    private String supId;

    public VehicleTm(String vehicleId, String vehicleModel, String vehicleNumber, String supplierId, int qty) {
    }

    public VehicleTm(String id, String model, String vehicle_number, String supId) {
        this.id = id;
        this.model = model;
        this.vehicle_number = vehicle_number;
        this.supId = supId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    @Override
    public String toString() {
        return "VehicleTm{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", vehicle_number='" + vehicle_number + '\'' +
                ", supId='" + supId + '\'' +
                '}';
    }
}
