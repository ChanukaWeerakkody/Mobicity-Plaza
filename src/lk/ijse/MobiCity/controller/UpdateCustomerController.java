package lk.ijse.MobiCity.controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MobiCity.bo.BOFactory;
import lk.ijse.MobiCity.bo.custom.impl.CustomerBOImpl;
import lk.ijse.MobiCity.dto.CustomerDTO;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateCustomerController {
    public AnchorPane subPane;
    public Button btnBack;
    public JFXTextField txtId;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXButton btnClear;
    public JFXButton btnUpdate;
    public JFXTextField txtContact;
    private CustomerBOImpl customerBO=(CustomerBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, subPane);
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            CustomerDTO customer = customerBO.searchCustomer(id);
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

    public void updateOnAction(ActionEvent actionEvent) {
        try {
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
            if (!customerBO.existsCustomer(txtId.getText())) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + txtId.getText()).show();
            }
            customerBO.updateCustomer(new CustomerDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),Integer.parseInt(txtContact.getText())));
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated successfully!").show();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + txtId.getText() + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillData(CustomerDTO customer) {
        txtId.setText(customer.getCusId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtContact.setText(String.valueOf(customer.getContact()));
    }
}
