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

public class SearchEmployeeController {
    public AnchorPane subPane;
    public Button btnBack;
    public JFXTextField txtId;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXButton btnClear;
    public JFXButton btnSearch;
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

    public void searchOnAction(ActionEvent actionEvent) {
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
