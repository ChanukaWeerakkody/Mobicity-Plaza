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
import lk.ijse.MobiCity.bo.custom.impl.ItemBOImpl;
import lk.ijse.MobiCity.dto.ItemDTO;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;
import lk.ijse.MobiCity.view.tdm.ItemTM;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemController {
    public AnchorPane subPane;
    public JFXTextField txtId;
    public JFXTextField txtQty;
    public JFXTextField txtBrand;
    public JFXTextField txtName;
    public TableView tblItem;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colBrand;
    public TableColumn colQty;
    public TableColumn colPrice;
    public JFXButton btnRefresh;
    public JFXButton btnClear;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnSearch;
    public JFXButton btnDelete;
    public JFXTextField txtPrice;
    private ItemBOImpl itemBO=(ItemBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            setItemInToTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void refreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM, subPane);
    }

    private void setItemInToTable() throws SQLException, ClassNotFoundException {
        tblItem.getItems().clear();
        try {
            ArrayList<ItemDTO> allItem=itemBO.getAllItem();
            for(ItemDTO item: allItem){
                tblItem.getItems().add(new ItemTM(item.getItemId(), item.getName(), item.getBrand(),item.getQty(),item.getPrice()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
        txtBrand.clear();
        txtQty.clear();
    }

    public void addOnAction(ActionEvent actionEvent) {
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
        if(btnAdd.getText().equalsIgnoreCase("ADD")){
            try {
                if(existItem(id)){
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }
                itemBO.saveItem(new ItemDTO(id,name,brand,qty,price));
                new Alert(Alert.AlertType.CONFIRMATION, "Save item successfully").show();
                tblItem.getItems().add(new ItemTM(id, name, brand,qty,price));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemBO.existsItem(id);
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.UPDATEITEM, subPane);
    }

    public void searchOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHITEM, subPane);
    }

    public void deleteOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELETEITEM, subPane);
    }
}
