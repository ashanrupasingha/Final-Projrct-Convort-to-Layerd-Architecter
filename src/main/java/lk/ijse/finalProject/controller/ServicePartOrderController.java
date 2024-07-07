package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.bo.BoFactory;
import lk.ijse.finalProject.bo.PartsBO;
import lk.ijse.finalProject.bo.ServiceBO;
import lk.ijse.finalProject.bo.ServicePartBO;
import lk.ijse.finalProject.dto.ServiceDTO;
import lk.ijse.finalProject.model.*;
import lk.ijse.finalProject.model.tm.CartTm;
import lk.ijse.finalProject.repository.ServiceRepo;
import lk.ijse.finalProject.repository.partsRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ServicePartOrderController {

    public JFXComboBox<String> cmbServiceId;
    public Label lblServicerName;
    public JFXComboBox<String > cmbPartCode;
    public Label lblpartname;
    public Label lblPartPrice;
    public Label lblQtyOnHand;
    public TextField txtQty;
    public TableView<CartTm> tblOrderCart;
    public TableColumn <?,?> colPartCode;
    public TableColumn <?,?> colDescription;
    public TableColumn <?,?> colQty;
    public TableColumn <?,?> colUnitPrice;
    public TableColumn <?,?> colTotal;
    public TableColumn <?,?> colAction;
    public Label lblNetTotal;
    public Label lblUnitPrice;
    public Label lblOrderDate;
    public Label lblPartId;
    private ObservableList<CartTm> obList = FXCollections.observableArrayList();
    private AnchorPane rootnode;
    ServiceBO serviceBO = (ServiceBO) BoFactory.getObject().getbo(BoFactory.BoType.Service);
    PartsBO partsBO = (PartsBO) BoFactory.getObject().getbo(BoFactory.BoType.Part);

    public void initialize() {
        setDate();
        getCurrentOrderId();
     //  getCustomerIds();
        getItemCodes();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colPartCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }
    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }
    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String id = cmbServiceId.getValue();
        try {
            ServiceDTO service = serviceBO.searchById(id);

            lblServicerName.setText(service.getService_package());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void getCurrentOrderId() {
        try {
            String currentId = partsBO.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            lblPartId.setText(nextOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("P00");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);
            return "P00" + ++idNum;
        }
        return "P001";
    }
    @FXML
    void cmbPartOnAction(ActionEvent event) {
        String code = cmbPartCode.getValue();

        try {
            Part part = partsRepo.searchById(code);
            if(part != null) {
                lblpartname.setText(part.getPart_name());
                lblPartPrice.setText(String.valueOf(part.getPrice()));
                lblQtyOnHand.setText(String.valueOf(part.getPart_qyt()));
            }

            txtQty.requestFocus();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbPartCode.getValue();
        String description = lblpartname.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblPartPrice.getText());
        double total = qty * unitPrice;
        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                tblOrderCart.refresh();
                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if(code.equals(colPartCode.getCellData(i))) {

                CartTm tm = obList.get(i);
                qty += tm.getQty();
                total = qty * unitPrice;

                tm.setQty(qty);
                tm.setTotal(total);

                tblOrderCart.refresh();

                calculateNetTotal();
                return;
            }
        }

       // CartTm tm = new CartTm(code, description, part, qty, unitPrice, total, btnRemove);
      //  obList.add(tm);

        tblOrderCart.setItems(obList);
        calculateNetTotal();
        txtQty.setText("");
    }
    private void getItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = Collections.singletonList(partsRepo.getCurrentId());

            for (String code : codeList) {
                obList.add(code);
            }
            cmbPartCode.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void calculateNetTotal() {
        int netTotal = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            netTotal += (double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }



    public void backOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNodE = FXMLLoader.load(this.getClass().getResource("/view/service_part_order.fxml"));
        rootnode.getChildren().clear();
        this.rootnode.getChildren().add(rootNodE);
       // new Pulse(rootNodE).play();
    }
}
