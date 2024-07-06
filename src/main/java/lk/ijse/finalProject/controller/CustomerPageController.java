package lk.ijse.finalProject.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.controller.Util.Regex;
import lk.ijse.finalProject.controller.Util.TextFeld;
import lk.ijse.finalProject.controller.impl.CustomerBO;
import lk.ijse.finalProject.controller.impl.CustomerBoimpl;
import lk.ijse.finalProject.model.Customer;
import lk.ijse.finalProject.model.DTO.CustomerDTO;
import lk.ijse.finalProject.model.tm.CustomerTm;
import lk.ijse.finalProject.repository.CustomerRepo2;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {

    public AnchorPane rootnode;
    public TextField txtcustomerid;
    public TextField txtAddress;
    public TextField txttelephone;
    public TextField txtCustomerName;
    public TableView<CustomerTm> tblCustomerView;
    public TableColumn<?,?> clmCusId;
    public TableColumn<?,?> clmName;
    public TableColumn<?,?> clmAddress;
    public TableColumn<?,?> clmPhone;
    CustomerBO customerBO = new CustomerBoimpl();


    private void getCurrentCustomerId() throws SQLException {

        try {
            String currentId = customerBO.getCurrentId();
            String nextCustomerId = generateNextCustomerId(currentId);
            txtcustomerid.setText(nextCustomerId);
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private String generateNextCustomerId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("C00");
            int idNum = Integer.parseInt(split[1]);
            return "C00" + ++idNum;
        }
        return "C001";
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTable();
        setCellValueFactory();
        getCurrentCustomerId();
    }

    private void setCellValueFactory() {
        clmCusId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmPhone.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void setTable() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDTO> cusList = customerBO.getAll();
            for (CustomerDTO customer : cusList){
                CustomerTm tm = new CustomerTm(
                        customer.getCustomer_id(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getTelephone()
                );
                obList.add(tm);
            }
            tblCustomerView.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private AnchorPane rootNode;




    public void saveAction(ActionEvent actionEvent) throws SQLException {
        String code = txtcustomerid.getText();
        String name = txtCustomerName.getText();
        String address = txtAddress.getText();
        String tel = txttelephone.getText();
        //Customer customer = new Customer(code,name,address,tel);

        if (isValied()) {
        try {
            boolean isSaved = customerBO.saveCustomer(new CustomerDTO(code, name, address, tel));
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer saved successfully").show();
                clearFields();
                setTable();
                getCurrentCustomerId();
            } else {
                new Alert(Alert.AlertType.ERROR,"Customer can't be saved").show();
            }
            setTable();
            setCellValueFactory();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


        }



    }

    public void btnupdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code = txtcustomerid.getText();
        String name = txtCustomerName.getText();
        String address = txtAddress.getText();
        String tel = txttelephone.getText();

        if (isValied()) {
            try {
                boolean isUpdate = customerBO.updateCustomer(new CustomerDTO(code, name, address, tel));
                if (isUpdate) {
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Update successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Cant update customer").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private void clearFields() {
        txtcustomerid.setText("");
        txtCustomerName.setText("");
        txtAddress.setText("");
        txttelephone.setText("");
    }

    public void txtValueFillOnAction(ActionEvent actionEvent) {
            String id = txtcustomerid.getText();
            try {
                CustomerDTO customer = customerBO.searchById(id);

                txtCustomerName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txttelephone.setText(customer.getTelephone());
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtcustomerid.getText();

        try {
            boolean isDeleted = customerBO.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer delete successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Customer delete unsuccessfully").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    

    public void txtCustomerNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setCustomerTextColor(TextFeld.CustomerName,txtCustomerName);
    }

    public void txtCustomeeTelephoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setCustomerTextColor(TextFeld.txttelephone,txttelephone);
    }

    public void txtCustomerAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setCustomerTextColor(TextFeld.txtAddress,txtAddress);
    }

    public void txtCustomerIDOnKeyReleased(KeyEvent keyEvent) {
    }
    public boolean isValied(){
        if (!Regex.setCustomerTextColor(TextFeld.CustomerName,txtCustomerName)) return false;
        if (!Regex.setCustomerTextColor(TextFeld.txttelephone,txttelephone)) return false;
        if (!Regex.setCustomerTextColor(TextFeld.txtAddress,txtAddress)) return  false;
        return true;
    }
}

