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
import lk.ijse.MobiCity.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.MobiCity.dto.EmployeeDTO;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;
import lk.ijse.MobiCity.view.tdm.EmployeeTM;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeController {
    public AnchorPane subPane;
    public JFXTextField txtId;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public TableView tblEmployee;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public JFXButton btnRefresh;
    public JFXButton btnClear;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnSearch;
    public JFXButton btnDelete;

    private EmployeeBOImpl employeeBO=(EmployeeBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        try {
            setEmployeeInToTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setEmployeeInToTable() throws SQLException, ClassNotFoundException {
        tblEmployee.getItems().clear();
        try {
            ArrayList<EmployeeDTO> allEmployee = employeeBO.getAllEmployee();
            for(EmployeeDTO employee: allEmployee){
                tblEmployee.getItems().add(new EmployeeTM(employee.getEmpId(), employee.getName(), employee.getAddress(),employee.getContact()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void refreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,subPane);
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtContact.clear();
        txtName.clear();
        txtAddress.clear();
    }

    boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeBO.existsEmployee(id);
    }

    public void addOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        int contact=Integer.parseInt(txtContact.getText());
        if(!id.matches("^(E)[-]?[0-9]{3}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid id").show();
            txtId.requestFocus();
            return;
        }else if(!name.matches("^[A-z ]{5,30}$")){
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
                tblEmployee.getItems().add(new EmployeeTM(id, name, address,contact));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.UPDATEEMPLOYEE,subPane);
    }

    public void searchOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHEMPLOYEE,subPane);
    }

    public void deleteOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELETEEMPLOYEE,subPane);
    }

}
