package lk.ijse.MobiCity.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MobiCity.bo.BOFactory;
import lk.ijse.MobiCity.bo.custom.impl.ItemBOImpl;
import lk.ijse.MobiCity.dto.ItemDTO;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateItemController {
    public AnchorPane subPane;
    public Button btnBack;
    public JFXTextField txtId;
    public JFXTextField txtBrand;
    public JFXTextField txtName;
    public JFXButton btnClear;
    public JFXButton btnUpdate;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;

    private ItemBOImpl itemBO=(ItemBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM,subPane);
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            ItemDTO item=itemBO.searchItem(id);
            if(item != null) {
                fillData(item);
            } else {
                new Alert(Alert.AlertType.WARNING, "Item Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtBrand.clear();
        txtQty.clear();
        txtPrice.clear();
    }

    public void updateOnAction(ActionEvent actionEvent) {
        try {
            String id=txtId.getText();
            String name=txtName.getText();
            String brand=txtBrand.getText();
            int qty=Integer.parseInt(txtQty.getText());
            double price=Double.parseDouble(txtPrice.getText());

            if(!txtId.getText().matches("^(I)[-]?[0-9]{3}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid id").show();
                txtId.requestFocus();
                return;
            }else if(!txtName.getText().matches("^[A-z ]{5,30}$")){
                new Alert(Alert.AlertType.ERROR, "Invalid name").show();
                txtName.requestFocus();
                return;
            }else if (!txtBrand.getText().matches("^[A-z ]{2,30}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid brand name").show();
                txtName.requestFocus();
                return;
            }else if(!txtQty.getText().matches("[0-9]+")){
                new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
                txtQty.requestFocus();
                return;
            }else if(!txtPrice.getText().matches("^[1-9][0-9]{1,9}$")){
                new Alert(Alert.AlertType.ERROR, "Invalid price").show();
                txtPrice.requestFocus();
                return;
            }
            if (!itemBO.existsItem(txtId.getText())) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + txtId.getText()).show();
            }
            itemBO.updateItem(new ItemDTO(txtId.getText(),txtName.getText(),txtBrand.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtPrice.getText())));
            new Alert(Alert.AlertType.CONFIRMATION, "Item Updated successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the item " + txtId.getText() + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillData(ItemDTO item) {
        txtId.setText(item.getItemId());
        txtName.setText(item.getName());
        txtBrand.setText(item.getBrand());
        txtQty.setText(String.valueOf(item.getQty()));
        txtPrice.setText(String.valueOf(item.getPrice()));
    }
}
