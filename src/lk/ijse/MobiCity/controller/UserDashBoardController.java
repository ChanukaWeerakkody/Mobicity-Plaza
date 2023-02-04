package lk.ijse.MobiCity.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;

import java.io.IOException;
import java.util.Optional;

public class UserDashBoardController {
    public AnchorPane mainPane;
    public JFXButton btnPurchase;
    public JFXButton btnCustomer;
    public JFXButton btnLogOut;
    public AnchorPane subPane;
    public Label lblDate;
    public Label lblTime;

    public void purchaseOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACEORDER, subPane);
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, subPane);
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure want to logOut?");
        Optional<ButtonType> option=alert.showAndWait();

        if(option.get().equals(ButtonType.OK)){
            Navigation.navigate(Routes.LOG,mainPane);
        }else return;
    }
}
