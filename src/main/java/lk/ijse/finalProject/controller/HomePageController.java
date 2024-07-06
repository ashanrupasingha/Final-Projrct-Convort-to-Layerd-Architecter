package lk.ijse.finalProject.controller;


import animatefx.animation.Pulse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.finalProject.repository.CustomerVehicleRepo;
import lk.ijse.finalProject.repository.OrderRepo;
import lk.ijse.finalProject.repository.PartRepo;
import lk.ijse.finalProject.repository.VehicleRepo;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    public Label partlabel;
    public Label orderslabel;
    public Label customersvehiclelable;
    @FXML
    private BarChart<?, ?> Barchart;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label lblCareSale;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblProfit;

    @FXML
    private PieChart pieChart;

    @FXML
    private AnchorPane rootNode;


    public void setCarsaleamuont () {
        try {
            String amount = VehicleRepo.getCarAmount();
            lblCareSale.setText(amount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    try{  String amount = CustomerVehicleRepo.getCarAmount();
        customersvehiclelable.setText(amount);

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        String amount = null;
        try {
            amount = PartRepo.getCarAmount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        partlabel.setText(amount);
        try {
            amount = OrderRepo.getCarAmount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        orderslabel.setText(amount);
    }



    public void setDatetext(){
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
        System.out.println(lblCareSale +"+"+ lblDate);
    }

    public void btnSalePageOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootnode = FXMLLoader.load(this.getClass().getResource("/view/salePage.fxml"));
        rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootnode);
        new Pulse(rootNode).play();

    }

    public void btnSettingPageOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootnode = FXMLLoader.load(this.getClass().getResource("/view/settingPage.fxml"));
        rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootnode);
        new Pulse(rootNode).play();

    }



    public void btnServicePageOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootnode = FXMLLoader.load(this.getClass().getResource("/view/servicePage.fxml"));
        rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootnode);
        new Pulse(rootNode).play();
    }

    public void btnPartspageOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootnode = FXMLLoader.load(this.getClass().getResource("/view/PartsPage.fxml"));
        rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootnode);
        new Pulse(rootNode).play();

    }


    public void CustomerAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootnode = FXMLLoader.load(this.getClass().getResource("/view/CustomerForm.fxml"));
        rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootnode);
        new Pulse(rootNode).play();
    }

    public void btnDashbordAction(ActionEvent actionEvent) throws IOException {
        BorderPane rootnode = FXMLLoader.load(this.getClass().getResource("/view/homePage.fxml"));
        borderPane.getChildren().clear();
        this.borderPane.getChildren().add(rootnode);
//        new Pulse(rootNode).play();

    }

    public void EmployeeAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootnode = FXMLLoader.load(this.getClass().getResource("/view/EmployeeForm.fxml"));
        rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootnode);
        new Pulse(rootNode).play();
    }

    public void SupplierAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootnode = FXMLLoader.load(this.getClass().getResource("/view/SupplierForm.fxml"));
        rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootnode);
        new Pulse(rootNode).play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series series = new XYChart.Series();
        series.setName("2003");
        series.getData().add(new XYChart.Data("A1",2323));
        series.getData().add(new XYChart.Data("A2",4687));
        series.getData().add(new XYChart.Data("A3",5678));

        XYChart.Series series2 = new XYChart.Series();
        series.setName("2003");
        series.getData().add(new XYChart.Data("A1",2323));
        series.getData().add(new XYChart.Data("A2",4687));
        series.getData().add(new XYChart.Data("A3",5678));

        Barchart.getData().addAll(series,series2);

        ObservableList<PieChart.Data> pieChartData;
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Part",56),
                new PieChart.Data("Payment",35),
                new PieChart.Data("Order",67));

        pieChart.getData().addAll(pieChartData);
        setDatetext();
        setCarsaleamuont();
    }

    public void OrderAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNodE = FXMLLoader.load(this.getClass().getResource("/view/orderForm.fxml"));
        rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootNodE);
        new Pulse(rootNode).play();
    }

    public void PlaceServiceAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/PlaceService.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(pane);
    }

    public void btnCustomersvehicleOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/CustomerVehicleRepo.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(pane);
    }
}
