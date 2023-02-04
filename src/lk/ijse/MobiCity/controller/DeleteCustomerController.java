package lk.ijse.MobiCity.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MobiCity.bo.BOFactory;
import lk.ijse.MobiCity.bo.custom.impl.CustomerBOImpl;
import lk.ijse.MobiCity.dto.CustomerDTO;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteCustomerController {
    public AnchorPane subPane;
    public Button btnBack;
    public JFXTextField txtId;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXButton btnClear;
    public JFXButton btnDelete;
    public JFXTextField txtContact;
    private CustomerBOImpl customerBO=(CustomerBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void txtIdOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        CustomerDTO customer = null;
        try {
            customer = customerBO.searchCustomer(id);
            if(customer != null) {
                fillData(customer);
            } else {
                new Alert(Alert.AlertType.WARNING, "Customer Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String cusId = txtId.getText();
        try {
            boolean delete = customerBO.deleteCustomer(cusId);
            if (delete){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Deleted Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(CustomerDTO customer) {
        txtId.setText(customer.getCusId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtContact.setText(String.valueOf(customer.getContact()));
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, subPane);
    }
}
