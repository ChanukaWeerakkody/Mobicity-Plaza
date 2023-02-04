package lk.ijse.MobiCity.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MobiCity.bo.BOFactory;
import lk.ijse.MobiCity.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.MobiCity.bo.custom.impl.PaymentBOImpl;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class AdminDashBoardController {
    public AnchorPane mainPane;
    public JFXButton btnDashboard;
    public JFXButton btnSupplier;
    public JFXButton btnItem;
    public JFXButton btnUser;
    public JFXButton btnEmployee;
    public JFXButton order;
    public JFXButton btnLogOut;
    public AnchorPane subPane;
    public Label lblActiveEmployee;
    public Label lblTodayInCome;
    public Label lblTotalInCome;
    public Label lblTime;
    public Label lblDate;
    public BarChart chartIncome;
    private PaymentBOImpl paymentBO=(PaymentBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    private EmployeeBOImpl employeeBO=(EmployeeBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize(){
        displayTodayInCome();
        displayTotalInCome();
        displayActiveEmployees();
        loadOrderDate();
        loadOrderTime();
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD, mainPane);
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER, subPane);
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM, subPane);
    }

    public void userOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.USER, subPane);
    }

    public void employeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE, subPane);
    }

    public void orderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ORDER, subPane);
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

    public void displayTodayInCome(){
        double income=0;
        try {

            income = paymentBO.getTodayIncome();
            if(income != 0) {
                lblTodayInCome.setText("$ "+ income);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayTotalInCome(){
        double totalIncome=0;
        try {
            totalIncome = paymentBO.getTotalIncome();
            if(totalIncome != 0) {
                lblTotalInCome.setText("$ "+ totalIncome);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayActiveEmployees(){
        int count=0;
        try {
            count = employeeBO.activeEmployees();
            if(count != 0) {
                lblActiveEmployee.setText(String.valueOf(count));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadOrderTime(){
        lblTime.setText(String.valueOf(LocalDate.now()));
        DateFormat df = new SimpleDateFormat(" HH:mm:ss");
        Date dateobj = new Date();
        lblTime.setText(df.format(dateobj));
    }

    private void loadOrderDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }
}
