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

public class SearchItemController {
    public AnchorPane subPane;
    public Button btnBack;
    public JFXTextField txtId;
    public JFXTextField txtBrand;
    public JFXTextField txtName;
    public JFXButton btnClear;
    public JFXButton btnSearch;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;
    private ItemBOImpl itemBO=(ItemBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM,subPane);
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtBrand.clear();
        txtQty.clear();
        txtPrice.clear();
    }

    public void searchOnAction(ActionEvent actionEvent) {
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

    private void fillData(ItemDTO item) {
        txtId.setText(item.getItemId());
        txtName.setText(item.getName());
        txtBrand.setText(item.getBrand());
        txtQty.setText(String.valueOf(item.getQty()));
        txtPrice.setText(String.valueOf(item.getPrice()));
    }
}
