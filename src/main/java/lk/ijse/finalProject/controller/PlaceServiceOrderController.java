package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.finalProject.bo.*;
import lk.ijse.finalProject.bo.impl.*;
import lk.ijse.finalProject.controller.impl.*;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.Util.Regex;
import lk.ijse.finalProject.Util.TextFeld;
import lk.ijse.finalProject.dto.PartsServiceDetailDTO;
import lk.ijse.finalProject.dto.VehicleDetailsDTO;
import lk.ijse.finalProject.model.tm.CartTm;
import lk.ijse.finalProject.repository.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceServiceOrderController implements Initializable {

    public TableView<CartTm> tblServiceOrder;
    public TableColumn<?,?> ClmServicePackage;
    public TableColumn<?,?> ClmCustomerID;
    public TableColumn<?,?> ClmPart;
    public TableColumn<?,?> ClmPartQTY;
    public TableColumn<?,?> clmQty;
    public JFXComboBox<String> comboServicePackage;
    public JFXComboBox<String> comboCustomerVehicleID;
    public JFXComboBox<String> comboPart;
    public TextField txtpartqty;
    public TableColumn<?,?> clmTotal;
    public TableColumn<?,?> clmRemove;
    public Label lblNetTotal;
    public JFXButton btnAddToCart;
    private ObservableList<CartTm> obList = FXCollections.observableArrayList();
    CustomerVehicleBO customerVehicleBO=new CustomerVehicleBoimpl();
    VehicleDetailBO vehicleDetailBO = new VehicleDetailBOImpl();
    ServicePartBO servicePartBO = new ServicePartBoimpl();
    PartsBO partsBO = new PartsBoimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCombo();
        setComboCusId();
        setComcoPart();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
     ClmServicePackage.setCellValueFactory(new PropertyValueFactory<>("servicePackage"));
     ClmCustomerID.setCellValueFactory(new PropertyValueFactory<>("cusId"));
     ClmPart.setCellValueFactory(new PropertyValueFactory<>("part"));
     ClmPartQTY.setCellValueFactory(new PropertyValueFactory<>("total"));
     clmRemove.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    private void setComcoPart() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> names = partsRepo.getNames();
            for (String nameList : names){
                obList.add(nameList);
            }
            comboPart.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setComboCusId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> id = CustomerVehicleRepo.getIds();
            for (String vehiId : id){
                obList.add(vehiId);
            }
            comboCustomerVehicleID.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> serviceList = ServiceRepo.getName();
            for (String service : serviceList) {
                obList.add(service);
            }
            comboServicePackage.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {

        int qty = Integer.parseInt(txtpartqty.getText());
        String part = comboPart.getValue();
        String cusId = comboCustomerVehicleID.getValue();
        String packageName = comboServicePackage.getValue();
if (isValied()) {
    double unitPrice = Double.parseDouble(txtpartqty.getText());
    double total = qty * unitPrice;
    JFXButton btnRemove = new JFXButton("remove");
    btnRemove.setCursor(Cursor.HAND);

    btnRemove.setOnAction((e) -> {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            int selectedIndex = tblServiceOrder.getSelectionModel().getSelectedIndex();
            obList.remove(selectedIndex);

            tblServiceOrder.refresh();
            calculateNetTotal();
        }
    });
    for (int i = 0; i < tblServiceOrder.getItems().size(); i++) {
        if (packageName.equals(ClmServicePackage.getCellData(i))) {

            CartTm tm = obList.get(i);
            qty += tm.getQty();
            total = qty * unitPrice;

            tm.setQty(qty);
            tm.setTotal(total);

            tblServiceOrder.refresh();

            calculateNetTotal();
            return;
        }
    }
    CartTm tm = new CartTm(packageName, cusId, part, qty, total, btnRemove);
    obList.add(tm);
    tblServiceOrder.setItems(obList);
}

    }

    private void calculateNetTotal() {
            int netTotal = 0;
            for (int i = 0; i < tblServiceOrder.getItems().size(); i++) {
                netTotal += (double) clmTotal.getCellData(i);
                lblNetTotal.setText(String.valueOf(netTotal));
            }
            clmTotal.setText(String.valueOf(netTotal));

    }

    public void btnPlaceServiceOnAction(ActionEvent actionEvent) throws SQLException {
        if (isValied()) {
            int qty = Integer.parseInt(txtpartqty.getText());
            String packageName = comboServicePackage.getValue();
            String part = comboPart.getValue();
            String cusVehiId = comboCustomerVehicleID.getValue();
            String serviceId = ServiceRepo.getPackageId(packageName);
            String part_id = PartRepo.getItemId(part);

            /*String cusId = customerVehicleBO.getCustomerId(cusVehiId);*/

            /*boolean isSaved = PlaceServiceRepo.saveDetails(ps);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Service placed successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Service placed unsuccessfully").show();
            }*/
            Connection connection = Dbconnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            try {
                System.out.println("Invoke first one");
                boolean isSaved = vehicleDetailBO.saveVehicleDetail(new VehicleDetailsDTO(serviceId,cusVehiId));
                if (!isSaved) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    new Alert(Alert.AlertType.ERROR,"Serviced placed unsuccessfully").show();
                    return;
                }
                    System.out.println("Invoke second one");
                System.out.println(part_id+" ,"+serviceId);
                    boolean saved = servicePartBO.saveServicePart(new PartsServiceDetailDTO(serviceId,part_id));
                    if (!saved) {
                        connection.rollback();
                        connection.setAutoCommit(true);
                        new Alert(Alert.AlertType.ERROR,"Serviced placed unsuccessfully").show();
                        return;
                    }
                        System.out.println("Invoke third one");
                        boolean isUpdated = partsBO.updatePartAfterService(qty,part_id);
                        if (!isUpdated) {
                            connection.rollback();
                            connection.setAutoCommit(true);
                            new Alert(Alert.AlertType.ERROR,"Serviced placed unsuccessfully").show();
                            return;
                        }
                            System.out.println("commited");
                            connection.commit();
                            new Alert(Alert.AlertType.CONFIRMATION,"Serviced placed successfully").show();

            } catch (Exception e){
                connection.rollback();
                connection.setAutoCommit(false);
                new Alert(Alert.AlertType.ERROR,"Serviced placed unsuccessfully").show();
            }
        }
    }
        public void txtReleased (KeyEvent keyEvent){
            Regex.setPlaceServiceOrderTextColor(TextFeld.txtpartqty, txtpartqty);
        }
        public boolean isValied () {

            if (!Regex.setPlaceServiceOrderTextColor(TextFeld.txtpartqty, txtpartqty)) return false;

            return true;
        }

    public void btnBillPrintOnAction(ActionEvent actionEvent) {


    }
}
