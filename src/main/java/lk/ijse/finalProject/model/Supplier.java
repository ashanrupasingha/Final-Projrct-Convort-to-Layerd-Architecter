package lk.ijse.finalProject.model;

public class Supplier {

    private String supplier_id;

    private  String supplier_name;

    private String supplier_contact_number;

    public Supplier() {

    }

    public Supplier(String supplier_id, String supplier_name, String supplier_contact_number) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.supplier_contact_number = supplier_contact_number;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_contact_number() {
        return supplier_contact_number;
    }

    public void setSupplier_contact_number(String supplier_contact_number) {
        this.supplier_contact_number = supplier_contact_number;
    }
}
