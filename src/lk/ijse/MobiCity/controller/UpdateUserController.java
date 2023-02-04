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

public class UpdateUserController {
    public AnchorPane subPane;
    public Button btnBack;
    public JFXTextField txtId;
    public JFXTextField txtPassword;
    public JFXTextField txtUsername;
    public JFXTextField txtRole;
    public JFXButton btnClear;
    public JFXButton btnupdate;
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

    public void updateOnAction(ActionEvent actionEvent) {
        try {
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
            if (!userBO.existsUser(txtId.getText())) {
                new Alert(Alert.AlertType.ERROR, "There is no such user associated with the id " + txtId.getText()).show();
            }
            userBO.updateUser(new UserDTO(txtId.getText(),txtRole.getText(),txtUsername.getText(),txtPassword.getText()));
            new Alert(Alert.AlertType.CONFIRMATION, "User Updated successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the user " + txtId.getText() + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillData(UserDTO user) {
        txtId.setText(user.getEmpId());
        txtRole.setText(user.getSystemRole());
        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
    }
}
