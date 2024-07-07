package lk.ijse.finalProject.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.bo.BoFactory;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.Util.Regex;
import lk.ijse.finalProject.Util.TextFeld;
import lk.ijse.finalProject.bo.ServiceBO;
import lk.ijse.finalProject.bo.ServiceBoimpl;
import lk.ijse.finalProject.dto.ServiceDTO;
import lk.ijse.finalProject.model.tm.ServiceTm;
import lk.ijse.finalProject.repository.ServiceRepo;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ServicePageController implements Initializable {




    public TextField txtserviceAmount;
    public TextField txtservicerid;
    public TextField txtservicePackage;
    public TableColumn<?,?> clmserviceId;
    public TableColumn<?,?> clmservicepackage;
    public TableColumn<?,?> clmserviceamount;
    public TableView tblServiceView;
    public AnchorPane rootNode;
    ServiceBO serviceBO = (ServiceBO) BoFactory.getObject().getbo(BoFactory.BoType.Service);


    private void getCurrentServiceId() throws SQLException, ClassNotFoundException {
        String currentId = serviceBO.getCurrentId();

        String nextCustomerId = generateNextCustomerId(currentId);
        txtservicerid.setText(nextCustomerId);
    }

    private String generateNextCustomerId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("S00");
            int idNum = Integer.parseInt(split[1]);
            return "S00" + ++idNum;
        }
        return "S001";
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtservicerid.getText();
        try {
            boolean isDeleted = serviceBO.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer delete successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Customer delete unsuccessfully").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnupdateOnAction(ActionEvent actionEvent) {
        String code = txtservicerid.getText();
        String servicepackage = txtservicePackage.getText();
        double serviceamount = Double.parseDouble(txtserviceAmount.getText());

        ServiceDTO service = new ServiceDTO(code, servicepackage, serviceamount);
        if (isValied()) {
            try {
                boolean isUpdate = serviceBO.updateCustomer(service);
                if (isUpdate) {
                    clearFields();
                    setTable();
                    setCellValueFactory();
                    new Alert(Alert.AlertType.CONFIRMATION, "Update successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Cant update service").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }


    public void txtValueFillOnAction(ActionEvent actionEvent) {

            String id = txtservicerid.getText();
            try {
                ServiceDTO service= serviceBO.searchById(id);

                txtservicePackage.setText(service.getService_package());
                txtserviceAmount.setText(String.valueOf(service.getService_amount()));

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }


    public void btnClearOnActioN(ActionEvent actionEvent) {clearFields();
    }

    public void saveAction(ActionEvent actionEvent) {
        String code = txtservicerid.getText();
        String servicepackage = txtservicePackage.getText();
        double serviceamount = Double.parseDouble(txtserviceAmount.getText());

        ServiceDTO service = new ServiceDTO(code, servicepackage, serviceamount);
        if (isValied()) {
            try {
                boolean isSaved = serviceBO.saveCustomer(service);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Service saved successfully").show();
                    clearFields();
                    setTable();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Service can't be saved").show();
                }
                setTable();
                setCellValueFactory();
                getCurrentServiceId();

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    private void setCellValueFactory() {
     clmserviceId.setCellValueFactory(new PropertyValueFactory<>("service_id"));
        clmservicepackage.setCellValueFactory(new PropertyValueFactory<>("service_package"));
        clmserviceamount.setCellValueFactory(new PropertyValueFactory<>("service_amount"));

    }
    @SneakyThrows
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTable();
        setCellValueFactory();
        getCurrentServiceId();
    }
    private void setTable() {
        ObservableList<ServiceTm> obList = FXCollections.observableArrayList();
        try {
            List<ServiceDTO> serviceList = serviceBO.getAll();
            for (ServiceDTO service : serviceList){
                ServiceTm tm = new ServiceTm(
                        service.getService_id(),
                        service.getService_package(),
                        service.getService_amount()
                );
                obList.add(tm);
                tblServiceView.setItems(obList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void clearFields() {
        txtservicerid.setText("");
        txtservicePackage.setText("");
        txtserviceAmount.setText("");

    }

    public void OrderAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootnode = FXMLLoader.load(this.getClass().getResource("/view/service_part_order.fxml"));
        rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootnode);


    }

    public void txtServicePackageOnKeyReleased(KeyEvent keyEvent) {
        Regex.setServiceTextColor(TextFeld.txtservicePackage,txtservicePackage);
    }

    public void txtServicePacakeOnKeyReleased(KeyEvent keyEvent) {
        Regex.setServiceTextColor(TextFeld.txtserviceAmount,txtserviceAmount);
    }
    public boolean isValied(){
        if (!Regex.setServiceTextColor(TextFeld.txtservicePackage,txtservicePackage)) return false;
        if (!Regex.setServiceTextColor(TextFeld.txtserviceAmount,txtserviceAmount)) return false;

        return true;
    }

    public void btnBillPrintOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("/home/ashan/Desktop/Final Project/finalProject/src/main/resources/Report/serviceReport2.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String,Object> data = new HashMap<>();
        data.put("service_id",txtservicerid.getText());
        data.put("service_amount",txtserviceAmount.getText());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,data,Dbconnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }


}
