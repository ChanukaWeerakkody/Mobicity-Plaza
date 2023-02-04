package lk.ijse.MobiCity.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MobiCity.bo.BOFactory;
import lk.ijse.MobiCity.bo.custom.impl.SupplierBOImpl;
import lk.ijse.MobiCity.dto.SupplierDTO;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateSupplierController {
    public AnchorPane subPane;
    public Button btnBack;
    public JFXTextField txtId;
    public JFXTextField txtBrand;
    public JFXTextField txtItemName;
    public JFXTextField txtName;
    public JFXButton btnClear;
    public JFXButton btnUpdate;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;

    private SupplierBOImpl supplierBO=(SupplierBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER, subPane);
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            SupplierDTO supplier=supplierBO.searchSupplier(id);
            if(supplier != null) {
                fillData(supplier);
            } else {
                new Alert(Alert.AlertType.WARNING, "Supplier Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtItemName.clear();
        txtBrand.clear();
        txtQty.clear();
        txtPrice.clear();
    }

    public void updateOnAction(ActionEvent actionEvent)  {
        try {
            if(!txtId.getText().matches("^(S)[-]?[0-9]{3}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid id").show();
                txtId.requestFocus();
                return;
            }else if(!txtName.getText().matches("^[A-z ]{5,30}$")){
                new Alert(Alert.AlertType.ERROR, "Invalid name").show();
                txtName.requestFocus();
                return;
            }else if (!txtItemName.getText().matches("^[A-z ]{5,30}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid name").show();
                txtItemName.requestFocus();
                return;
            }else if(!txtBrand.getText().matches("^[A-z ]{5,30}$")){
                new Alert(Alert.AlertType.ERROR, "Invalid name").show();
                txtBrand.requestFocus();
                return;
            }else if(!txtQty.getText().matches("[0-9]+")){
                new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
                txtQty.requestFocus();
                return;
            } else if(!txtPrice.getText().matches("^[1-9][0-9]{1,9}$")){
                new Alert(Alert.AlertType.ERROR, "Invalid price").show();
                txtPrice.requestFocus();
                return;
            }
            supplierBO.updateSupplier(new SupplierDTO(txtId.getText(),txtName.getText(),txtName.getText(),txtBrand.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtPrice.getText())));
            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the supplier " + txtId.getText() + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillData(SupplierDTO supplier) {
        txtId.setText(supplier.getSupId());
        txtName.setText(supplier.getName());
        txtItemName.setText(supplier.getItemName());
        txtBrand.setText(supplier.getBrand());
        txtQty.setText(String.valueOf(supplier.getQty()));
        txtPrice.setText(String.valueOf(supplier.getPrice()));
    }
}
