package lk.ijse.finalProject.model.DTO;

public class EmployeeDTO {
    private String employee_id;
    private String employee_name;
    private String address;
    private String telephone;

    public EmployeeDTO(String employeeId, String orderDiscription, String customerId, int orderQty) {
    }

    public EmployeeDTO(String employee_id, String employee_name, String address, String telephone) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.address = address;
        this.telephone = telephone;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id='" + employee_id + '\'' +
                ", employee_name='" + employee_name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
