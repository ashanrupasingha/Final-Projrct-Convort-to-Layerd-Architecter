package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.sun.source.tree.IfTree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.controller.Util.Regex;
import lk.ijse.finalProject.controller.Util.TextFeld;
import lk.ijse.finalProject.controller.impl.OrderBo;
import lk.ijse.finalProject.controller.impl.OrderBoimpl;
import lk.ijse.finalProject.controller.impl.SaleBo;
import lk.ijse.finalProject.controller.impl.SaleBoimpl;
import lk.ijse.finalProject.model.Customer;
import lk.ijse.finalProject.model.DTO.VehicleDTO;
import lk.ijse.finalProject.model.Part;
import lk.ijse.finalProject.model.Vehicle;
import lk.ijse.finalProject.model.tm.PartTm;
import lk.ijse.finalProject.model.tm.SaleTm;
import lk.ijse.finalProject.model.tm.VehicleTm;
import lk.ijse.finalProject.repository.CustomerRepo2;
import lk.ijse.finalProject.repository.SaleRepo;
import lk.ijse.finalProject.repository.SupplierRepo;
import lk.ijse.finalProject.repository.partsRepo;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SalePageController implements Initializable {

    public AnchorPane rootNode;
    public AnchorPane salePage;
    public TextField txtvehicleid;
    public TextField txtpvehiclemodel;
    public TextField txtvihicletnumber;
    public JFXButton saveid;
    public TableView<SaleTm> tbl;
    public TableColumn<?,?> ClmCode;
    public TableColumn<?,?> ClmModel;
    public TableColumn<?,?> Clmnumber;
    public TableColumn<?,?> ClmSupplier;
    public JFXComboBox<String> comboSupplierId;
    public TextField txtVehicleQty;
    public TableColumn<?,?> clmQty;
    SaleBo saleBo =new SaleBoimpl();

    private void getCurrentSaleId() throws SQLException, ClassNotFoundException {
        String currentId = saleBo.getCurrentId();

        String nextvehicleId = generateNextVehicleId(currentId);
        txtvehicleid.setText(nextvehicleId);
    }

    private String generateNextVehicleId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("V00");
            int idNum = Integer.parseInt(split[1]);
            return "V00" + ++idNum;
        }
        return "V001";
    }
    private void clearFields() {
        txtvehicleid.setText("");
        txtpvehiclemodel.setText("");
        txtvihicletnumber.setText("");
        txtVehicleQty.clear();
        comboSupplierId.setValue("");
    }
    @SneakyThrows
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCombo();
        setTable();
        setCellValueFactory();
        getCurrentSaleId();

    }

    private void setCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> supList = SupplierRepo.getSupplierId();
            for (String supplier : supList) {
                obList.add(supplier);
                comboSupplierId.setItems(obList);
            }
        }catch (RuntimeException | SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        ClmCode.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        ClmModel.setCellValueFactory(new PropertyValueFactory<>("vehicle_model"));
        Clmnumber.setCellValueFactory(new PropertyValueFactory<>("vehicle_Number"));
        ClmSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }


    public void SaveOnAction(ActionEvent actionEvent) throws IOException {
        String code = txtvehicleid.getText();
        String name = txtpvehiclemodel.getText();
        String number = txtvihicletnumber.getText();
        String supplier = comboSupplierId.getValue();
        int qty = Integer.parseInt(txtVehicleQty.getText());
        Vehicle vehicle = new Vehicle(code, name, number, supplier,qty);
       if (isValied()) {
            try {
                boolean isSaved = SaleRepo.save(vehicle);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Vehicle saved successfully").show();
                    clearFields();
                    setTable();
                    getCurrentSaleId();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Vehicle can't be saved").show();
                }
                setTable();
                setCellValueFactory();
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void enretOnAction(ActionEvent actionEvent) {
        String id =txtvehicleid.getText();
        try {
            VehicleDTO vehicle = saleBo.searchById(id);

            txtpvehiclemodel.setText(vehicle.getVehicle_model());
            txtvihicletnumber.setText(vehicle.getVehicle_number());
            comboSupplierId.setValue(vehicle.getSupplier_id());
            txtVehicleQty.setText(String.valueOf(vehicle.getQty()));
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }




    public void btndeleteOnAction(ActionEvent actionEvent) {
        String id = txtvehicleid.getText();
        try {
            boolean isDeleted = saleBo.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle delete successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Vehicle delete unsuccessfully").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    public void UpdatesOnAction(ActionEvent actionEvent) {
        String code = txtvehicleid.getText();
        String model = txtpvehiclemodel.getText();
        String number = txtvihicletnumber.getText();
        String supplierId = (String) comboSupplierId.getValue();
        int qty = Integer.parseInt(txtVehicleQty.getText());
        VehicleDTO vehicle = new VehicleDTO(code, model, number, supplierId,qty);
       // if (isValied()) {
            try {
                boolean isUpdate = saleBo.updateCustomer(vehicle);
                if (isUpdate) {
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Update successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Cant update vehicle").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
       // }
    }

    public void ClearOnAction(ActionEvent actionEvent) {


            clearFields();

    }
    private void setTable() {
        ObservableList<SaleTm> obList = FXCollections.observableArrayList();
        try {
            List<VehicleDTO> partList = saleBo.getAll();
            for (VehicleDTO vehicle : partList){
                SaleTm tm = new SaleTm(
                        vehicle.getVehicle_id(),
                        vehicle.getVehicle_model(),
                        vehicle.getVehicle_number(),
                        vehicle.getSupplier_id(),
                        vehicle.getQty()
                );
                obList.add(tm);
                tbl.setItems(obList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void txtQtyKeyReleased(KeyEvent keyEvent) {

    }
    public void txtVehicleNumberOnKeyReleased(KeyEvent keyEvent) {

   }

    public void txtVehicleModelOnKeyReleased(KeyEvent keyEvent) {

    }
    public boolean isValied(){



        return true;
    }


}
