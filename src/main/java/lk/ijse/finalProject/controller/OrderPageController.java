package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.bo.*;
import lk.ijse.finalProject.bo.impl.*;
import lk.ijse.finalProject.Util.Regex;
import lk.ijse.finalProject.Util.TextFeld;
import lk.ijse.finalProject.controller.impl.*;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.dto.OrderDTO;
import lk.ijse.finalProject.model.Order;
import lk.ijse.finalProject.model.PlaceOrder;
import lk.ijse.finalProject.model.tm.orderTm;
import lk.ijse.finalProject.repository.CustomerRepo;
import lk.ijse.finalProject.repository.VehicleRepo;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {

    @FXML
    private TableView<orderTm> tblOrderView;
    @FXML
    private TableColumn<orderTm, String> clmorderId;
    @FXML
    private TableColumn<orderTm, String> clmorderdiscription;
    @FXML
    private TableColumn<orderTm, Integer> clmorderqty;
    @FXML
    private TableColumn<orderTm, String> clmCustomerCode;
    @FXML
    private TableColumn<orderTm, String> clmvehicleCode;
    @FXML
    private TextField txtOrderid;
    @FXML
    private TextField txtorderqty;
    @FXML
    private TextField txtorderdiscription;
    @FXML
    private AnchorPane rootNodE;
    @FXML
    private JFXComboBox<String> comboCusId;
    @FXML
    private JFXComboBox<String> comboVehicleId;
    OrderBo orderBoimpl =new OrderBoimpl();
    CustomerVehicleBO vehicleBO = new CustomerVehicleBoimpl();
    VehicleBO vehicleBoimpl = new VehicleBoimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTable();
        setCellValueFactory();
        setComboCusId();
        setComboVehicleId();
    }

    private void setComboVehicleId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> vehicleId = VehicleRepo.getVehicleId();
            obList.addAll(vehicleId);
            comboVehicleId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setComboCusId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> id = CustomerRepo.getId();
            obList.addAll(id);
            comboCusId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtOrderid.clear();
        txtorderdiscription.clear();
        txtorderqty.clear();
        comboCusId.setValue("");
        comboVehicleId.setValue("");
    }

    private void setCellValueFactory() {
        clmorderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        clmorderdiscription.setCellValueFactory(new PropertyValueFactory<>("order_discription"));
        clmorderqty.setCellValueFactory(new PropertyValueFactory<>("order_qty"));
        clmCustomerCode.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        clmvehicleCode.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
    }

    private void setTable() {
        ObservableList<orderTm> obList = FXCollections.observableArrayList();
        try {
            List<OrderDTO> orderList = orderBoimpl.getAll();
            for (OrderDTO order : orderList) {
                orderTm tm = new orderTm(
                        order.getOrder_id(),
                        order.getOrder_discription(),
                        order.getCustomer_id(),
                        order.getOrder_qty(),
                        order.getVehicle_id()
                );
                obList.add(tm);
            }
            tblOrderView.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void txtValueFillOnAction(ActionEvent actionEvent) {
        String id = txtOrderid.getText();
        try {
            OrderDTO order = orderBoimpl.searchById(id);

            txtorderdiscription.setText(order.getOrder_discription());
            comboCusId.setValue(order.getCustomer_id());
            txtorderqty.setText(String.valueOf(order.getOrder_qty()));
            comboVehicleId.setValue(order.getVehicle_id());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtOrderid.getText();
        try {
            boolean isDeleted = orderBoimpl.deleteCustomer(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order deleted successfully").show();
                setTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order delete unsuccessful").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void btnupdateOnAction(ActionEvent actionEvent) {
        String code = txtOrderid.getText();
        String name = txtorderdiscription.getText();
        String customerCode = comboCusId.getValue();
        int qty = Integer.parseInt(txtorderqty.getText());
        String vehicleCode = comboVehicleId.getValue();
        OrderDTO order = new OrderDTO(code, name, customerCode, qty, vehicleCode);
        if (isValied()) {
            try {
                boolean isUpdate = orderBoimpl.updateCustomer(order);
                if (isUpdate) {
                    clearFields();
                    setTable();
                    new Alert(Alert.AlertType.CONFIRMATION, "Update successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Cannot update order").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    private void btnClearOnActioN(ActionEvent actionEvent) {
        clearFields();
    }

    @FXML
    private void saveAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code = txtOrderid.getText();
        String name = txtorderdiscription.getText();
        String customerCode = comboCusId.getValue();
        int qty = Integer.parseInt(txtorderqty.getText());
        String vehicleCode = comboVehicleId.getValue();

        var order = new Order(code, name, customerCode, qty, vehicleCode);
        PlaceOrder po = new PlaceOrder(order);
        boolean isSaved = orderBoimpl.saveCustomer(new OrderDTO(code,name,customerCode,qty, vehicleCode));
        if (isSaved) {
            setTable();
            new Alert(Alert.AlertType.CONFIRMATION, "Order placed successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order placement unsuccessful").show();
        }
        Connection connection = Dbconnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isSave = orderBoimpl.saveCustomer(new OrderDTO(code, name, customerCode, qty, vehicleCode));
            if (!isSave) {
                connection.rollback();
                connection.setAutoCommit(true);
                new Alert(Alert.AlertType.ERROR,"Order placed unsuccessfully").show();
                return;
                }
            System.out.println("isSAved");
            boolean isUpdated = vehicleBoimpl.updateAfterServicedQty(vehicleCode,qty);
            if (!isUpdated) {
                connection.rollback();
                connection.setAutoCommit(true);
                new Alert(Alert.AlertType.ERROR,"Placed order unsuccessfully").show();
                return ;
            }
            connection.commit();
            connection.setAutoCommit(true);
            new Alert(Alert.AlertType.CONFIRMATION,"Placed order successfully").show();
        }catch (Exception e){
            connection.rollback();
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    private void txtOrderDisOnKeyReleased(KeyEvent keyEvent) {
        Regex.setOrderTextColor(TextFeld.txtorderdiscription, txtorderdiscription);
    }

    @FXML
    private void txtOrderqtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setOrderTextColor(TextFeld.txtorderqty, txtorderqty);
    }

    private boolean isValied() {
        if (!Regex.setOrderTextColor(TextFeld.txtorderqty, txtorderqty)) return false;
        return Regex.setOrderTextColor(TextFeld.txtorderdiscription, txtorderdiscription);
    }


}
