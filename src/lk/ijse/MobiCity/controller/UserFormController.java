package lk.ijse.MobiCity.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MobiCity.bo.BOFactory;
import lk.ijse.MobiCity.bo.custom.impl.UserBOImpl;
import lk.ijse.MobiCity.dto.UserDTO;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;
import lk.ijse.MobiCity.view.tdm.UserTM;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFormController {
    public AnchorPane subPane;
    public JFXTextField txtId;
    public JFXTextField txtPassword;
    public JFXTextField txtUsername;
    public TableView tblUser;
    public TableColumn colId;
    public TableColumn colRole;
    public TableColumn colUsername;
    public TableColumn colPassword;
    public JFXButton btnRefresh;
    public JFXButton btnClear;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnSearch;
    public JFXButton btnDelete;
    public JFXComboBox cmbRole;

    private String [] roleList={"Admin","User"};
    private UserBOImpl userBO=(UserBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("systemRole"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        try {
            seUserInToTable();
            addRoleOnAction();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void seUserInToTable() throws SQLException, ClassNotFoundException {
        tblUser.getItems().clear();
        try {
            ArrayList<UserDTO> allUser=userBO.getAllUser();
            for(UserDTO user: allUser){
                tblUser.getItems().add(new UserTM(user.getEmpId(), user.getSystemRole(), user.getUsername(),user.getPassword()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void refreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.USER,subPane);
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void addOnAction(ActionEvent actionEvent) {
        String role=(String) cmbRole.getValue();
        if(!txtId.getText().matches("^(E)[-]?[0-9]{3}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid id").show();
            txtId.requestFocus();
            return;
        }else if (!txtUsername.getText().matches("^[A-z ]{5,30}$")) {
            new Alert(Alert.AlertType.ERROR, "Username should be at least 3 characters long").show();
            txtUsername.requestFocus();
            return;
        }else if(!txtPassword.getText().matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            txtPassword.requestFocus();
            return;
        }
        if(btnAdd.getText().equalsIgnoreCase("ADD")){
            try {
                if(existEmployee(txtId.getText())){
                    new Alert(Alert.AlertType.ERROR, txtId.getText() + " already exists").show();
                }
                userBO.saveUser(new UserDTO(txtId.getText(),role,txtUsername.getText(),txtPassword.getText()));
                new Alert(Alert.AlertType.CONFIRMATION, "Save user successfully").show();
                tblUser.getItems().add(new UserTM(txtId.getText(),role,txtUsername.getText(),txtPassword.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return userBO.existsUser(id);
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.UPDATEUSER,subPane);
    }

    public void searchOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHUSER,subPane);
    }

    public void deleteOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELETEUSER,subPane);
    }

    public void addRoleOnAction() {
        List<String> lists=new ArrayList<>();
        for(String role : roleList){
            lists.add(role);
        }
        ObservableList roleData= FXCollections.observableArrayList(lists);
        cmbRole.setItems(roleData);
    }

    public void clearField(){
        txtId.clear();
        txtUsername.clear();
        txtPassword.clear();
    }
}
