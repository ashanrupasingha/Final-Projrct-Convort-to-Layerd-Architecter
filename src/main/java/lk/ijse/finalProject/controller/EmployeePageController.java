package lk.ijse.finalProject.controller;

import javafx.scene.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.controller.Util.Regex;
import lk.ijse.finalProject.controller.Util.TextFeld;
import lk.ijse.finalProject.controller.impl.CustomerVehicleBO;
import lk.ijse.finalProject.controller.impl.CustomerVehicleBoimpl;
import lk.ijse.finalProject.controller.impl.EmployeeBo;
import lk.ijse.finalProject.controller.impl.EmployeeBoimpl;
import lk.ijse.finalProject.model.Customer;
import lk.ijse.finalProject.model.DTO.EmployeeDTO;
import lk.ijse.finalProject.model.Employee;
import lk.ijse.finalProject.model.tm.CustomerTm;
import lk.ijse.finalProject.model.tm.EmployeeTm;
import lk.ijse.finalProject.repository.CustomerRepo2;
import lk.ijse.finalProject.repository.EmployeeRepo;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class EmployeePageController implements  Initializable{

    public TextField txtEmployeerid;
    public TextField txtEmployeeName;
    public TextField txtemployeetelephone;
    public TextField txtemployeeAddress;
    public TableView<EmployeeTm> tblEmployeeView;
    public TableColumn<?,?> clmemployeId;
    public TableColumn<?,?> clmemployeeName;
    public TableColumn<?,?> clmemployeeAddress;
    public TableColumn<?,?> clmemployeePhone;
    EmployeeBo employeeBo =new EmployeeBoimpl();


    private void getCurrentEmployeeId() throws SQLException, ClassNotFoundException {
        String currentId = employeeBo.getCurrentId();

        String nextEmployeeId = generateNextEmployeeId(currentId);
        txtEmployeerid.setText(nextEmployeeId);
    }

    private String generateNextEmployeeId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("E00");
            int idNum = Integer.parseInt(split[1]);
            return "E00" + ++idNum;
        }
        return "E001";
    }
    @SneakyThrows
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTable();
        setCellValueFactory();
        getCurrentEmployeeId();
    }

    private void clearFields() {
        txtEmployeerid.setText("");
        txtEmployeeName.setText("");
        txtemployeeAddress.setText("");
        txtemployeetelephone.setText("");
    }
    private void setCellValueFactory() {
        clmemployeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmemployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmemployeeAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmemployeePhone.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void setTable() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
        try {
            List<EmployeeDTO> EmployeeList = employeeBo.getAll();
            for (EmployeeDTO employee : EmployeeList){
                EmployeeTm tm = new EmployeeTm(
                        employee.getEmployee_id(),
                        employee.getEmployee_name(),
                        employee.getAddress(),
                        employee.getTelephone()
                );
                obList.add(tm);
                tblEmployeeView.setItems(obList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtValueFillOnAction(ActionEvent actionEvent) {
        String id = txtEmployeerid.getText();
        try {
            EmployeeDTO employee = employeeBo.searchById(id);

            txtEmployeeName.setText(employee.getEmployee_name());
            txtemployeeAddress.setText(employee.getAddress());
            txtemployeetelephone.setText(employee.getTelephone());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void saveAction(ActionEvent actionEvent) {
        String code = txtEmployeerid.getText();
        String name = txtEmployeeName.getText();
        String address = txtemployeeAddress.getText();
        String tel = txtemployeetelephone.getText();
        EmployeeDTO employee = new EmployeeDTO(code, name, address, tel);
        if (isValied()) {
            try {
                boolean isSaved = employeeBo.saveCustomer(employee);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee saved successfully").show();
                    clearFields();
                    setTable();
                    getCurrentEmployeeId();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Employee can't be saved").show();
                }
                setTable();
                setCellValueFactory();
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();

    }

    public void btnupdateOnAction(ActionEvent actionEvent) {
        String code = txtEmployeerid.getText();
        String name = txtEmployeeName.getText();
        String address = txtemployeeAddress.getText();
        String tel = txtemployeetelephone.getText();
        EmployeeDTO employee = new EmployeeDTO(code, name, address, tel);
        if (isValied()) {
            try {
                boolean isUpdate = employeeBo.updateCustomer(employee);
                if (isUpdate) {
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Update successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Cant update Employee").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtEmployeerid.getText();

            try {
                boolean isDeleted = employeeBo.deleteCustomer(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee delete successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Employee delete unsuccessfully").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }


    public void txtEmployeeNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setEmployeeTextColor(TextFeld.txtEmployeeName,txtEmployeeName);
    }

    public void txtEmployeeTelephoneOnKeyReleased(KeyEvent keyEvent) {

        Regex.setEmployeeTextColor(TextFeld.txtemployeetelephone,txtemployeetelephone);
    }

    public void txtEmployeeAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setEmployeeTextColor(TextFeld.txtemployeeAddress,txtemployeeAddress);
    }
    public boolean isValied(){
        if (!Regex.setEmployeeTextColor(TextFeld.txtEmployeeName,txtEmployeeName)) return false;
        if (!Regex.setEmployeeTextColor(TextFeld.txtemployeetelephone,txtemployeetelephone)) return false;
        if (!Regex.setEmployeeTextColor(TextFeld.txtemployeeAddress,txtemployeeAddress)) return  false;
        return true;
    }
}

