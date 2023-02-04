package lk.ijse.MobiCity.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MobiCity.bo.BOFactory;
import lk.ijse.MobiCity.bo.custom.impl.UserBOImpl;
import lk.ijse.MobiCity.dto.UserDTO;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserController {
    public AnchorPane subPane;
    public Button btnBack;
    public JFXTextField txtId;
    public JFXTextField txtPassword;
    public JFXTextField txtUsername;
    public JFXTextField txtRole;
    public JFXButton btnClear;
    public JFXButton btnDelete;
    private UserBOImpl userBO=(UserBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.USER,subPane);
    }

    public void idOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            UserDTO user=userBO.searchUser(id);
            if(user != null) {
                fillData(user);
            } else {
                new Alert(Alert.AlertType.WARNING, "User Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtRole.clear();
        txtUsername.clear();
        txtPassword.clear();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String emp_id = txtId.getText();
        try {
            boolean delete =  userBO.deleteUser(emp_id);
            if (delete){
                new Alert(Alert.AlertType.INFORMATION,"User Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Deleted Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(UserDTO user) {
        txtId.setText(user.getEmpId());
        txtRole.setText(user.getSystemRole());
        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
    }
}
