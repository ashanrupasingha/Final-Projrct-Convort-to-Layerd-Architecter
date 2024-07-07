package lk.ijse.finalProject.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lk.ijse.finalProject.Util.TextFeld.*;

public class Regex {
    public static boolean isTextFieldValid(TextFeld textField, String text) {
        String filed = "";
         if (textField.equals(txtusermane)) {
            filed = "^[A-z|\\\\s]{3,}$";
        } else if (textField.equals(txtPassword)) {
            filed = "^([A-Z0-9]{3,})$";
        }else if (textField.equals(txtReEnterPassword)) {
            filed = "^([A-Z0-9]{3,})$";
        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public static boolean setTextColor(TextFeld location, javafx.scene.control.TextField textField) {
        if (Regex.isTextFieldValid(location, textField.getText())) {
            textField.setStyle("-fx-text-fill: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            return true;
        } else {
            textField.setStyle("-fx-text-fill: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }

    public static boolean isCustomerTextFieldValid(TextFeld textField, String text) {
        String filed = "";

         if (textField.equals(CustomerName)) {
            filed = "^[A-z|\\\\s]{3,}$";
        } else if (textField.equals(txtAddress)) {
            filed = "^[A-z|\\\\s]{3,}$";
        } else if (textField.equals(txttelephone)) {
            filed = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
        }


        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public static boolean setCustomerTextColor(TextFeld location,javafx.scene.control.TextField textField) {
        if (Regex.isCustomerTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-fill: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            return true;
        }else{
            textField.setStyle("-fx-text-fill: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }
    public static boolean isEmployeeTextFieldValid(TextFeld textField, String text) {
        String filed = "";


          if (textField.equals(txtEmployeeName)) {
            filed = "^[A-z|\\\\s]{3,}$";
        } else if (textField.equals(txtemployeeAddress)) {
            filed = "^[A-z|\\\\s]{3,}$";
        } else if (textField.equals(txtemployeetelephone)){
            filed = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";

        }


        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public static boolean setEmployeeTextColor(TextFeld location,javafx.scene.control.TextField textField) {
        if (Regex.isEmployeeTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-fill: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            return true;
        }else{
            textField.setStyle("-fx-text-fill: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }
    public static boolean isPartTextFieldValid(TextFeld textField, String text) {
        String filed = "";


        if (textField.equals(txtpartname)) {
            filed = "^[A-z|\\\\s]{3,}$";
        } else if (textField.equals(txtpartqty)) {
            filed = "^\\d+$";
        } else if (textField.equals(txtpartqty1)){
            filed =  "^([0-9]){1,}[.]([0-9]){1,}$";

        }


        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public static boolean setPartTextColor(TextFeld location,javafx.scene.control.TextField textField) {
        if (Regex.isPartTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-fill: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            return true;
        }else{
            textField.setStyle("-fx-text-fill: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }
    public static boolean isSaleTextFieldValid(TextFeld textField, String text) {
        String filed = "";


        if (textField.equals(txtpvehiclemodel)) {
          filed =        "^[A-z]{1,3}-/d{1,4}$";
        } else if (textField.equals(txtvihicletnumber)) {
            filed = "^[A-z|\\\\s]{3,}$";
        }else if (textField.equals(txtVehicleQty)) {
            filed = "^\\d+$";
        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public static boolean setSaleTextColor(TextFeld location,javafx.scene.control.TextField textField) {
        if (Regex.isSaleTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-fill: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            return true;
        }else{
            textField.setStyle("-fx-text-fill: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }
    public static boolean isServiceTextFieldValid(TextFeld textField, String text) {
        String filed = "";


        if (textField.equals(txtservicePackage)) {
            filed = "^[A-z|\\\\s]{3,}$";
        } else if (textField.equals(txtserviceAmount)) {
            filed = "^([0-9]){1,}[.]([0-9]){1,}$";
        }


        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public static boolean setServiceTextColor(TextFeld location,javafx.scene.control.TextField textField) {
        if (Regex.isServiceTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-fill: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            return true;
        }else{
            textField.setStyle("-fx-text-fill: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }
    public static boolean isSupplierTextFieldValid(TextFeld textField, String text) {
        String filed = "";


        if (textField.equals(txtsupplierName)) {
            filed = "^[A-z|\\\\s]{3,}$";
        } else if (textField.equals(txtsuppliertel)) {
            filed = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
        }


        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public static boolean setSupplierTextColor(TextFeld location,javafx.scene.control.TextField textField) {
        if (Regex.isSupplierTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-fill: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            return true;
        }else{
            textField.setStyle("-fx-text-fill: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }
    public static boolean isOrderTextFieldValid(TextFeld textField, String text) {
        String filed = "";


         if (textField.equals(txtorderqty)) {
            filed = "^\\d+$";
        }else if (textField.equals(txtorderdiscription)){
             filed ="^[A-z|\\s]{3,}$";
         }


        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public static boolean setOrderTextColor(TextFeld location,javafx.scene.control.TextField textField) {
        if (Regex.isOrderTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-fill: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            return true;
        }else{
            textField.setStyle("-fx-text-fill: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }
    public static boolean isCustomerVehicleTextFieldValid(TextFeld textField, String text) {
        String filed = "";



          if (textField.equals(txtVehicleNumber)) {
            filed = "^[A-z|\\\\s]{3,}$";
        }


        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public static boolean setCustomerVehicleTextColor(TextFeld location,javafx.scene.control.TextField textField) {
        if (Regex.isCustomerVehicleTextFieldValid(location, textField.getText())){
//            textField.setStyle("-fx-border-color: Green;");
//            textField.setStyle("-fx-border-color: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            return true;
        }else{
//            textField.setStyle("-fx-border-color: Red;");
//            textField.setStyle("-fx-border-color: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }
    public static boolean isPlaceServiceOrderTextFieldValid(TextFeld textField, String text) {
        String filed = "";


        if (textField.equals(txtpartqty)) {
            filed = "^\\d+$";
        }


        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }
    public static boolean setPlaceServiceOrderTextColor(TextFeld location,javafx.scene.control.TextField textField) {
        if (Regex.isPlaceServiceOrderTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-fill: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            return true;
        }else{
            textField.setStyle("-fx-text-fill: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }

}


