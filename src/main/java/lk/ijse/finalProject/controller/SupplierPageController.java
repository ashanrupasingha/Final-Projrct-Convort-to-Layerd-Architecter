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
import lk.ijse.finalProject.Util.Regex;
import lk.ijse.finalProject.Util.TextFeld;
import lk.ijse.finalProject.bo.SupplierBO;
import lk.ijse.finalProject.bo.SupplierBoimpl;
import lk.ijse.finalProject.dto.SupplierDTO;
import lk.ijse.finalProject.model.tm.SupplierTm;
import lk.ijse.finalProject.repository.SupplierRepo;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierPageController implements Initializable {

    public TextField txtsupplierrid;
    public TextField txtsupplierName;
    public TextField txtsuppliertel;
    public TableView<SupplierTm> tblSupplierView;
    public TableColumn<?,?> clmsupplierId;
    public TableColumn<?,?> clmsuppliernamw;
    public TableColumn<?,?> clmtelephone;
    SupplierBO supplierBO =new SupplierBoimpl();
    private void getCurrentSupplierId() throws SQLException {
        String currentId = SupplierRepo.getCurrentId();

        String nextCustomerId = generateNextCustomerId(currentId);
        txtsupplierrid.setText(nextCustomerId);
    }

    private String generateNextCustomerId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("S00");
            int idNum = Integer.parseInt(split[1]);
            return "S00" + ++idNum;
        }
        return "S001";
    }

    public void txtValueFillOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtsupplierrid.getText();
        SupplierDTO supplier= supplierBO.searchById(id);

        txtsupplierName.setText(supplier.getSupplier_name());
        txtsuppliertel.setText(String.valueOf(supplier.getSupplier_contact_number()));

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtsupplierrid.getText();
        try {
            boolean isDeleted = supplierBO.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier delete successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Supplier delete unsuccessfully").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnupdateOnAction(ActionEvent actionEvent) {
        String code = txtsupplierrid.getText();
        String suppliername = txtsupplierName.getText();
        String suppliertel = txtsuppliertel.getText();

        SupplierDTO supplier = new SupplierDTO(code, suppliername, suppliertel);
        if (isValied()) {
            try {
                boolean isUpdate = supplierBO.updateCustomer(supplier);
                if (isUpdate) {
                    clearFields();
                    setTable();
                    setCellValueFactory();
                    new Alert(Alert.AlertType.CONFIRMATION, "Update successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Cant update Supplier").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    public void btnClearOnActioN(ActionEvent actionEvent) {clearFields();
    }

    public void saveAction(ActionEvent actionEvent) {
        String code = txtsupplierrid.getText();
        String Suppliername = txtsupplierName.getText();
        String SupplierTel = txtsuppliertel.getText();

        SupplierDTO supplier = new SupplierDTO(code, Suppliername, SupplierTel);
        if (isValied()) {
            try {
                boolean isSaved = supplierBO.saveCustomer(supplier);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Service saved successfully").show();
                    clearFields();
                    setTable();
                    getCurrentSupplierId();
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
    @SneakyThrows
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTable();
        setCellValueFactory();
        getCurrentSupplierId();
    }
    private void setTable() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();
        try {
            List<SupplierDTO> supplierList = supplierBO.getAll();
            for (SupplierDTO supplier : supplierList){
                SupplierTm tm = new SupplierTm(
                        supplier.getSupplier_id(),
                        supplier.getSupplier_name(),
                        supplier.getSupplier_contact_number()
                );
                obList.add(tm);
                tblSupplierView.setItems(obList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void clearFields() {
        txtsupplierrid.setText("");
        txtsupplierName.setText("");
        txtsuppliertel.setText("");

    }
    private void setCellValueFactory() {
        clmsupplierId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        clmsuppliernamw.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        clmtelephone.setCellValueFactory(new PropertyValueFactory<>("supplier_tel"));

    }

    public void txtSupplierNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setSupplierTextColor(TextFeld.txtsupplierName,txtsupplierName);
    }

    public void txtSupplierTelephoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setSupplierTextColor(TextFeld.txtsuppliertel,txtsuppliertel);
    }
    public boolean isValied(){
        if (!Regex.setSupplierTextColor(TextFeld.txtsupplierName,txtsupplierName)) return false;
        if (!Regex.setSupplierTextColor(TextFeld.txtsuppliertel,txtsuppliertel)) return false;

        return true;
    }
}
