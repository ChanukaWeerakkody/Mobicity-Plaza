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
import lk.ijse.MobiCity.bo.custom.impl.CustomerBOImpl;
import lk.ijse.MobiCity.dto.CustomerDTO;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;
import lk.ijse.MobiCity.view.tdm.CustomerTM;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {
    public AnchorPane subPane;
    public JFXTextField txtId;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public JFXButton btnRefresh;
    public JFXButton btnClear;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnSearch;
    public JFXButton btnDelete;
    public JFXTextField txtContact;
    private CustomerBOImpl customerBO=(CustomerBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        try {
            setCustomerInToTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerInToTable() throws SQLException, ClassNotFoundException {
        tblCustomer.getItems().clear();
        try {
            ArrayList<CustomerDTO> allCustomer = customerBO.getAllCustomer();
            for(CustomerDTO customer: allCustomer){
                tblCustomer.getItems().add(new CustomerTM(customer.getCusId(), customer.getName(), customer.getAddress(),customer.getContact()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void refreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, subPane);
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    public void addOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        int contact=Integer.parseInt(txtContact.getText());

        if(!txtId.getText().matches("^(C)[-]?[0-9]{3}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid id").show();
            txtId.requestFocus();
            return;
        }else if(!txtName.getText().matches("^[A-z ]{5,30}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        }else if (!txtAddress.getText().matches("^[A-z 0-9 \\/\\,]{2,50}[A-z 0-9]{1,50}$")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtAddress.requestFocus();
            return;
        }else if(!txtContact.getText().matches("^(07|03|01)[0-9]{8}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid contact number").show();
            txtContact.requestFocus();
            return;
        }
        if(btnAdd.getText().equalsIgnoreCase("ADD")){
            try {
                if(existCustomer(id)){
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }
                customerBO.saveCustomer(new CustomerDTO(id,name,address,contact));
                new Alert(Alert.AlertType.CONFIRMATION, "Save customer successfully").show();
                tblCustomer.getItems().add(new CustomerTM(id, name, address,contact));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerBO.existsCustomer(id);
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.UPDATECUSTOMER, subPane);
    }

    public void searchOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHCUSTOMER, subPane);
    }

    public void deleteOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELETECUSTOMER, subPane);
    }
}
