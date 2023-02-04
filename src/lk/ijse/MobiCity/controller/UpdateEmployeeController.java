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

public class UpdateEmployeeController {
    public AnchorPane subPane;
    public Button btnBack;
    public JFXTextField txtId;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXButton btnClear;
    public JFXButton btnupdate;
    private EmployeeBOImpl employeeBO=(EmployeeBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,subPane);
    }

    public void idOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            EmployeeDTO employee = employeeBO.searchEmployee(id);
            if(employee != null) {
                fillData(employee);
            } else {
                new Alert(Alert.AlertType.WARNING, "Employee Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void updateOnAction(ActionEvent actionEvent) {
        try {
            if(!txtId.getText().matches("^(E)[-]?[0-9]{3}$")) {
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
            if (!employeeBO.existsEmployee(txtId.getText())) {
                new Alert(Alert.AlertType.ERROR, "There is no such employee associated with the id " + txtId.getText()).show();
            }
            employeeBO.updateEmployee(new EmployeeDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),Integer.parseInt(txtContact.getText())));
            new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated successfully!").show();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the employee " + txtId.getText() + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillData(EmployeeDTO employee) {
        txtId.setText(employee.getEmpId());
        txtName.setText(employee.getName());
        txtAddress.setText(employee.getAddress());
        txtContact.setText(String.valueOf(employee.getContact()));
    }

    public void clearField(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
    }
}
