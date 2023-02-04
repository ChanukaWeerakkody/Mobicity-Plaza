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
import lk.ijse.MobiCity.bo.custom.impl.SupplierBOImpl;
import lk.ijse.MobiCity.dto.SupplierDTO;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;
import lk.ijse.MobiCity.view.tdm.SupplierTM;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierController {
    public AnchorPane subPane;
    public JFXTextField txtId;
    public JFXTextField txtBrand;
    public JFXTextField txtItemName;
    public JFXTextField txtName;
    public TableView tblSupplier;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colItemName;
    public TableColumn colBrand;
    public TableColumn colQty;
    public TableColumn colPrice;
    public JFXButton btnRefresh;
    public JFXButton btnClear;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnSearch;
    public JFXButton btnDelete;
    public JFXTextField txtQty;
    public JFXTextField txtPrice;

    private SupplierBOImpl supplierBO=(SupplierBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            setSupplierInToTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setSupplierInToTable() throws SQLException, ClassNotFoundException {
        tblSupplier.getItems().clear();
        try {
            ArrayList<SupplierDTO> allSupplier=supplierBO.getAllSupplier();
            for(SupplierDTO supplier: allSupplier){
                tblSupplier.getItems().add(new SupplierTM(supplier.getSupId(), supplier.getName(), supplier.getItemName(),supplier.getBrand(),supplier.getQty(),supplier.getPrice()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void refreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER,subPane);
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtItemName.clear();
        txtBrand.clear();
        txtQty.clear();
        txtPrice.clear();
    }

    public void addOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String name=txtName.getText();
        String itemName=txtItemName.getText();
        String brand=txtBrand.getText();
        int qty=Integer.parseInt(txtQty.getText());
        double price=Double.parseDouble(txtPrice.getText());

        if(!txtId.getText().matches("^(S)[-]?[0-9]{3}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid id").show();
            txtId.requestFocus();
            return;
        }else if(!name.matches("^[A-z ]{5,30}$")){
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
        if(btnAdd.getText().equalsIgnoreCase("ADD")){
            try {
                if(existSupplier(id)){
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }
                supplierBO.saveSupplier(new SupplierDTO(id,name,itemName,brand,qty,price));
                new Alert(Alert.AlertType.CONFIRMATION, "Save supplier successfully").show();

                tblSupplier.getItems().add(new SupplierTM(id, name, itemName,brand,qty,price));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierBO.existsSupplier(id);
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.UPDATESUPPLIER,subPane);
    }

    public void searchOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSUPPLIER,subPane);
    }

    public void deleteOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELETESUPPLIER,subPane);
    }
}
