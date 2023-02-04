package lk.ijse.MobiCity.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MobiCity.bo.BOFactory;
import lk.ijse.MobiCity.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.MobiCity.dto.EmployeeDTO;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;
import java.io.IOException;
import java.sql.SQLException;

public class AddEmployeeController {
    public AnchorPane pane;
    public JFXTextField txtId;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXButton btnClear;
    public JFXButton btnAdd;
    public Button btnBack;
    private EmployeeBOImpl employeeBO=(EmployeeBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void clearOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void addOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        int contact=Integer.parseInt(txtContact.getText());
        if(!name.matches("^[A-z ]{5,30}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        }else if (!address.matches("^[A-z 0-9 \\/\\,]{2,50}[A-z 0-9]{1,50}$")) {
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
                if(existEmployee(id)){
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }
                employeeBO.saveEmployee(new EmployeeDTO(id,name,address,contact));
                new Alert(Alert.AlertType.CONFIRMATION, "Save employee successfully").show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeBO.existsEmployee(id);
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOG,pane);
    }

    private void clearField(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
    }
}
