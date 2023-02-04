package lk.ijse.MobiCity.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MobiCity.bo.BOFactory;
import lk.ijse.MobiCity.bo.custom.impl.PaymentBOImpl;
import lk.ijse.MobiCity.db.DBConnection;
import lk.ijse.MobiCity.dto.PaymentDTO;
import lk.ijse.MobiCity.view.tdm.PaymentTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderFormController {
    public AnchorPane subPane;
    public TableView tblOrder;
    public TableColumn colOrderId;
    public TableColumn colCustomerId;
    public TableColumn colBilledDate;
    public TableColumn colTotal;
    public TableColumn colPay;
    public TableColumn colBalance;
    public JFXTextField txtSearch;
    public JFXButton btnReport;
    private PaymentBOImpl paymentBO=(PaymentBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize() throws SQLException, ClassNotFoundException {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colBilledDate.setCellValueFactory(new PropertyValueFactory<>("billedDate"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colPay.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));

        setCustomerInToTable();
    }

    private void setCustomerInToTable() throws SQLException, ClassNotFoundException {
        tblOrder.getItems().clear();
        try {
            ArrayList<PaymentDTO> allOrder=paymentBO.getAllPayment();
            for(PaymentDTO payment: allOrder){
                tblOrder.getItems().add(new PaymentTM(payment.getOrderId(),payment.getCusId(), payment.getBilledDate(), payment.getTotal(),payment.getPayment(),payment.getBalance()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtOrderIdOnAction(ActionEvent actionEvent) {
    }

    public void reportOnAction(ActionEvent actionEvent) {
        HashMap<String, Object> hm = new HashMap<>();
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/MobiCity/report/Orders.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, DBConnection.getDbConnection().getConnection());
//            JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
