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

public class SearchSupplierController {
    public AnchorPane subPane;
    public Button btnBack;
    public JFXTextField txtId;
    public JFXTextField txtBrand;
    public JFXTextField txtItemName;
    public JFXTextField txtName;
    public JFXButton btnClear;
    public JFXButton btnSearch;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;

    private SupplierBOImpl supplierBO=(SupplierBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER, subPane);
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtItemName.clear();
        txtBrand.clear();
        txtQty.clear();
        txtPrice.clear();
    }

    public void searchOnAction(ActionEvent actionEvent) {
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

    private void fillData(SupplierDTO supplier) {
        txtId.setText(supplier.getSupId());
        txtName.setText(supplier.getName());
        txtItemName.setText(supplier.getItemName());
        txtBrand.setText(supplier.getBrand());
        txtQty.setText(String.valueOf(supplier.getQty()));
        txtPrice.setText(String.valueOf(supplier.getPrice()));
    }
}
