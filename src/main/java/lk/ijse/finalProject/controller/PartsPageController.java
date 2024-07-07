package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.finalProject.Util.Regex;
import lk.ijse.finalProject.Util.TextFeld;
import lk.ijse.finalProject.bo.BoFactory;
import lk.ijse.finalProject.bo.PartsBO;
import lk.ijse.finalProject.bo.PartsBoimpl;
import lk.ijse.finalProject.dto.PartDTO;
import lk.ijse.finalProject.model.Part;
import lk.ijse.finalProject.model.tm.PartTm;
import lk.ijse.finalProject.repository.SupplierRepo;
import lk.ijse.finalProject.repository.partsRepo;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PartsPageController implements Initializable {


    public TextField txtpartqty;
    public JFXButton saveid;
    public TextField txtpartname;
    public TextField txtpartid;
    public AnchorPane ParstPage;
    public JFXComboBox<String > SupplierId;
    public TableView<PartTm> tblpart;
    public TableColumn<?,?> Clmitem;
    public TableColumn<?,?> ClmPrice;
    public TableColumn<?,?> ClmQty;
    public TableColumn<?,?> ClmTotal;
    public TextField txtpartqty1;
    public TableColumn Clmprice1;
    PartsBO partsBO = (PartsBO) BoFactory.getObject().getbo(BoFactory.BoType.Part);
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCombo();
        setTable();
        setCellValueFactory();
        getCurrentPartsId();
    }
    private void getCurrentPartsId() throws SQLException, ClassNotFoundException {
        String currentId = partsBO.getCurrentId();

        String nextpartId = genaratepartId(currentId);
        txtpartid.setText(nextpartId);
    }

    private String genaratepartId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("P00");
            int idNum = Integer.parseInt(split[1]);
            return "P00" + ++idNum;
        }
        return "P001";
    }

    private void setCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> supList = SupplierRepo.getSupplierId();
            for (String supplier : supList) {
                obList.add(supplier);
                SupplierId.setItems(obList);
            }
        }catch (RuntimeException | SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        Clmitem.setCellValueFactory(new PropertyValueFactory<>("part_id"));
        ClmPrice.setCellValueFactory(new PropertyValueFactory<>("part_name"));
        ClmQty.setCellValueFactory(new PropertyValueFactory<>("part_qty"));
        Clmprice1.setCellValueFactory(new PropertyValueFactory<>("price"));
        ClmTotal.setCellValueFactory(new PropertyValueFactory<>("Supplier_id"));
    }


    public void SaveOnAction(ActionEvent actionEvent) {
        String code = txtpartid.getText();
        String name = txtpartname.getText();
        int qty = Integer.parseInt(txtpartqty.getText());
        double price = Double.parseDouble(txtpartqty1.getText());
        String supid = String.valueOf(SupplierId.getValue());
        Part part = new Part(code, name, qty, price, supid);
        if (isValied()) {
            try {
                boolean isSaved = partsRepo.save(part);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "  Part Saved").show();
                    clearFields();
                    setTable();
                    getCurrentPartsId();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Part Unsaved").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }
    public void UpdatesOnAction(ActionEvent actionEvent) {
        String code = txtpartid.getText();
        String name = txtpartname.getText();
        int qty = Integer.parseInt(txtpartqty.getText());
        double price = Double.parseDouble(txtpartqty1.getText());

        String supid = String.valueOf(SupplierId.getValue());
        PartDTO part = new PartDTO(code, name, qty, price, supid);
        if (isValied()) {
            try {
                boolean isUpdate = partsBO.updatePart(part);
                if (isUpdate) {
                    clearFields();
                    setTable();
                    setCellValueFactory();
                    new Alert(Alert.AlertType.CONFIRMATION, "Update successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Cant update customer").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    private void clearFields() {
        txtpartid.setText("");
        txtpartname.setText("");
        txtpartqty.setText("");
        txtpartqty1.setText("");
        SupplierId.setValue("");
    }


    public void enretOnAction(ActionEvent actionEvent) {
        String id =txtpartid.getText();
        try {
            PartDTO part = partsBO.searchById(id);

            txtpartname.setText(part.getPart_name());
            txtpartqty.setText(String.valueOf(part.getPart_qyt()));
            txtpartqty1.setText(String.valueOf(part.getPrice()));
            SupplierId.setValue(part.getSupplier_id());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btndeleteOnAction(ActionEvent actionEvent) {

        String id = txtpartid.getText();
        try {
            boolean isDeleted = partsBO.deletePart(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer delete successfully").show();
                clearFields();
                setTable();
            } else {
                new Alert(Alert.AlertType.ERROR,"Customer delete unsuccessfully").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void ClearOnAction(ActionEvent actionEvent) {

        clearFields();
    }
    private void setTable() {
        ObservableList<PartTm> obList = FXCollections.observableArrayList();
        try {
            List<PartDTO> partList = partsBO.getAll();
            for (PartDTO part : partList){
                PartTm tm = new PartTm(
                        part.getPart_id(),
                        part.getPart_name(),
                        part.getPart_qyt(),
                        part.getPrice(),
                        part.getSupplier_id()
                );
                obList.add(tm);
                tblpart.setItems(obList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtPartNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setPartTextColor(TextFeld.txtpartname,txtpartname);

    }

    public void txtPartqtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setPartTextColor(TextFeld.txtpartqty,txtpartqty);
    }

    public void txtpriceeOnKeyReleased(KeyEvent keyEvent) {
        Regex.setPartTextColor(TextFeld.txtpartqty1,txtpartqty1);
    }
    public boolean isValied(){
        if (!Regex.setPartTextColor(TextFeld.txtpartname,txtpartname)) return false;
        if (!Regex.setPartTextColor(TextFeld.txtpartqty,txtpartqty)) return false;
        if (!Regex.setPartTextColor(TextFeld.txtpartqty1,txtpartqty1)) return  false;
        return true;
    }
}


