package lk.ijse.finalProject.model.DTO;

import java.util.List;

public class CustomerDTO {
    private String customer_id;
    private String name;
    private String address;
    private String telephone;

    public CustomerDTO(String customer_id, String name, String address, String telephone) {
        this.customer_id = customer_id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }

    public CustomerDTO(List<String> ids) {

    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id='" + customer_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
