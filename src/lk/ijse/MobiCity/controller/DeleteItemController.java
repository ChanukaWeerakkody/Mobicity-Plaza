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

public class DeleteItemController {
    public AnchorPane subPane;
    public Button btnBack;
    public JFXTextField txtId;
    public JFXTextField txtBrand;
    public JFXTextField txtName;
    public JFXButton btnClear;
    public JFXButton btnDelete;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;

    private ItemBOImpl itemBO=(ItemBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM,subPane);
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        ItemDTO item = null;
        try {
            item=itemBO.searchItem(id);
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

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            boolean delete =  itemBO.deleteItem(id);
            if (delete){
                new Alert(Alert.AlertType.INFORMATION,"Item Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Deleted Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
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
