package lk.ijse.MobiCity.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MobiCity.bo.BOFactory;
import lk.ijse.MobiCity.bo.custom.impl.PaymentBOImpl;
import lk.ijse.MobiCity.bo.custom.impl.PurchaseOrderBOImpl;
import lk.ijse.MobiCity.dto.*;
import lk.ijse.MobiCity.view.tdm.PlaceOrderTM;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class PurchaseOrderController {
    public AnchorPane subPane;
    public JFXComboBox<String> cmbCustomer;
    public JFXTextField txtCrustName;
    public JFXTextField txtCrustAddress;
    public JFXTextField txtCustNumber;
    public Label lblOrderId;
    public Label lblOrderDate;
    public Label lblOrderTime;
    public JFXComboBox<String> cmbItem;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnClear;
    public JFXButton btnAdd;
    public TableView tblOrder;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colAction;
    public JFXButton btnPlaceOrder;
    public Label lblT;
    public JFXButton btnprint;
    public JFXTextField txtgetPayment;
    public double allTotal;
    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();
    private PurchaseOrderBOImpl purchaseOrderBO=(PurchaseOrderBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PURCHASEORDER);
    private PaymentBOImpl paymentBO=(PaymentBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize(){
        setCellValueFactory();
        generateNewOrderId();
        loadCustomerIds();
        loadItemCodes();
        loadOrderDate();
        loadOrderTime();
    }

    public void cmbCustomerOnAction() {
        String id = String.valueOf(cmbCustomer.getValue());
        try {
            CustomerDTO customer = purchaseOrderBO.searchCustomer(id);
            fillCustomerFields(customer);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbItemOnAction() {
        String id = String.valueOf(cmbItem.getValue());
        try {
            ItemDTO item = purchaseOrderBO.searchItem(id);
            fillItemFields(item);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(ItemDTO item) {
        txtDescription.setText(item.getName());
        txtQtyOnHand.setText(String.valueOf(item.getQty()));
        txtUnitPrice.setText(String.valueOf(item.getPrice()));
    }

    private void fillCustomerFields(CustomerDTO customer) {
        txtCrustName.setText(customer.getName());
        txtCrustAddress.setText(customer.getAddress());
        txtCustNumber.setText(String.valueOf(customer.getContact()));
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
    }

    private void generateNewOrderId() {
        try {
            String lastOrderId=purchaseOrderBO.generateNewOrderId();
            lblOrderId.setText(lastOrderId);
            if (lastOrderId == null) {
                lblOrderId.setText("O00001");
            } else {
                String[] split = lastOrderId.split("[O]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newOrderId = String.format("O%05d", lastDigits);
                lblOrderId.setText(newOrderId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadOrderTime(){
        lblOrderTime.setText(String.valueOf(LocalDate.now()));
        DateFormat df = new SimpleDateFormat(" HH:mm:ss");
        Date dateobj = new Date();
        lblOrderTime.setText(df.format(dateobj));
    }

    private void loadCustomerIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = purchaseOrderBO.loadCustomerId();
            for (String id : idList) {
                observableList.add(id);
            }
            cmbCustomer.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = purchaseOrderBO.loadItemCodes();
            for (String code : itemList) {
                observableList.add(code);
            }
            cmbItem.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }

    public void AddOnAction(ActionEvent actionEvent) {
        if (!txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText()) <= 0 ||
                Integer.parseInt(txtQty.getText()) > Integer.parseInt(txtQtyOnHand.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtQty.requestFocus();
            txtQty.selectAll();
            return;
        }
        String code = String.valueOf(cmbItem.getValue());
        String desc = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = unitPrice * qty;
        Button btnDelete = new Button("Delete");
        allTotal=0;
        txtQty.setText("");
        if (!obList.isEmpty()) {

            L1:
            for (int i = 0; i < tblOrder.getItems().size(); i++) {
                if (colCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);
                    total = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);
                    tblOrder.refresh();
                    allTotal+=total;
                    lblT.setText(String.valueOf(allTotal));
                    return;
                }
            }
        }
        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                PlaceOrderTM tm = (PlaceOrderTM) tblOrder.getSelectionModel().getSelectedItem();
                tblOrder.getItems().removeAll(tblOrder.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new PlaceOrderTM(code, desc, qty, unitPrice, total, btnDelete));
        tblOrder.setItems(obList);

        for (int i=0; i<obList.size(); i++){
            PlaceOrderTM o = obList.get(i);
            allTotal+=o.getTotal();
        }

        lblT.setText(String.valueOf(allTotal));
        PlaceOrderDTO placeOrder=new PlaceOrderDTO(code,desc,qty,unitPrice,total);
        try {
            purchaseOrderBO.savePlaceOrder(placeOrder);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) {
        saveOrder(lblOrderId.getText(),LocalDate.now(),cmbCustomer.getValue());
        double finalTotal= Double.parseDouble(lblT.getText());
        double pay=Double.parseDouble(txtgetPayment.getText());
        double balance=(pay-finalTotal);

        PaymentDTO paymentDTO = new PaymentDTO(lblOrderId.getText(), cmbCustomer.getValue(), lblOrderDate.getText(), finalTotal, pay, balance);
        try {
            boolean isPay = purchaseOrderBO.savePayment(paymentDTO);
            if(isPay){
                new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblOrder.getItems().clear();
        txtQty.clear();
    }

    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId) {
        try {
            OrderDTO orderDTO=new OrderDTO(orderId,orderDate,customerId);
            return purchaseOrderBO.purchaseOrder(orderDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void printOnaction(ActionEvent actionEvent) {
    }
}
