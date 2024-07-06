package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.finalProject.controller.impl.CustomerVehicleBO;
import lk.ijse.finalProject.controller.impl.CustomerVehicleBoimpl;
import lk.ijse.finalProject.model.CustomerVehicle;
import lk.ijse.finalProject.model.DTO.CustomerVehicleDTO;
import lk.ijse.finalProject.model.tm.CustomerVehicleTm;
import lk.ijse.finalProject.repository.CustomerRepo;
import lk.ijse.finalProject.repository.CustomerVehicleRepo;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerVehicleController implements Initializable {
    public TextField txtCustomer_vehicle_id;
    public TextField txtCustomerId;
    public AnchorPane rootNode;
    public TextField txtVehicleNumber;
    public TableView<CustomerVehicleTm> tblCustomervehicleView;
    public TableColumn<?,?> clmCUstomerVehicleID;
    public TableColumn<?,?> clmCustomerID;
    public TableColumn<?,?> clmvehiclenumber;
    public JFXComboBox<String> comboCustomerId;
    CustomerVehicleBO customervehicleBO =new CustomerVehicleBoimpl();


    private void getCurrentServiceId() throws SQLException {
        String currentId = CustomerVehicleRepo.getCurrentId();

        String nextCustomerId = generateNextCustomerId(currentId);
        txtCustomer_vehicle_id.setText(nextCustomerId);
    }

    private String generateNextCustomerId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("CV00");
            int idNum = Integer.parseInt(split[1]);
            return "CV00" + ++idNum;
        }
        return "CV001";
    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTable();
        setCellValueFactory();
        getCurrentServiceId();
        setComboCustomerId();

    }

    private void setComboCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> ids = CustomerRepo.getId();
            for (String id : ids){
                obList.add(id);
            }
            comboCustomerId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void saveAction(ActionEvent actionEvent) {
        String code = txtCustomer_vehicle_id.getText();
        String servicepackage = comboCustomerId.getValue();
        String serviceamount = txtVehicleNumber.getText();

        CustomerVehicle customerVehicle = new CustomerVehicle(code, servicepackage, serviceamount);
        if (isValied()) {
            try {
                boolean isSaved = customervehicleBO.saveCustomer(customerVehicle);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Service saved successfully").show();
                    clearFields();
                    setTable();
                    getCurrentServiceId();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Service can't be saved").show();
                }
                setTable();
                setCellValueFactory();


            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void btnClearOnActioN(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnupdateOnAction(ActionEvent actionEvent) {
        String code = txtCustomer_vehicle_id.getText();
        String servicepackage = comboCustomerId.getValue();
        String serviceamount = txtVehicleNumber.getText();

        CustomerVehicle customerVehicle = new CustomerVehicle(code, servicepackage, serviceamount);
        if (isValied()) {
            try {
                boolean isUpdate = customervehicleBO.updateCustomer(customerVehicle);
                if (isUpdate) {
                    clearFields();
                    setTable();
                    setCellValueFactory();
                    new Alert(Alert.AlertType.CONFIRMATION, "Update successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Cant update Customer Vehicle").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtCustomer_vehicle_id.getText();
        try {
            boolean isDeleted = customervehicleBO.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Vehicle delete successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Customer Vehicle delete unsuccessfully").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void clearFields() {
        txtCustomer_vehicle_id.setText("");
        comboCustomerId.setValue("");
        txtVehicleNumber.setText("");

    }
    private void setCellValueFactory() {
        clmCUstomerVehicleID.setCellValueFactory(new PropertyValueFactory<>("customervehicle_id"));
        clmCustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        clmvehiclenumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));

    }
    private void setTable() {
        ObservableList<CustomerVehicleTm> obList = FXCollections.observableArrayList();
        try {
            List<CustomerVehicleDTO> serviceList = customervehicleBO.getAll();
            for (CustomerVehicleDTO customerVehicle : serviceList){
                CustomerVehicleTm tm = new CustomerVehicleTm(
                        customerVehicle.getCustomervehicle_id(),
                        customerVehicle.getCustomer_id(),
                        customerVehicle.getVehicleNumber()
                );
                obList.add(tm);
                tblCustomervehicleView.setItems(obList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void txtValueFillOnAction(ActionEvent actionEvent) {
        String id = txtCustomer_vehicle_id.getText();
        try {
            CustomerVehicleDTO customerVehicle= customervehicleBO.searchById(id);

            comboCustomerId.setValue(customerVehicle.getCustomer_id());
            txtVehicleNumber.setText(customerVehicle.getVehicleNumber());


        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void setTxtCustomerVehicleReleased(KeyEvent keyEvent) {


    }

    public void txtReleased(KeyEvent keyEvent) {

    }
    public boolean isValied(){



        return true;
    }
}
