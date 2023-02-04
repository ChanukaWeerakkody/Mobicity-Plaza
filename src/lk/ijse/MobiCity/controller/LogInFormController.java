package lk.ijse.MobiCity.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import lk.ijse.MobiCity.bo.BOFactory;
import lk.ijse.MobiCity.bo.custom.impl.UserBOImpl;
import lk.ijse.MobiCity.util.Navigation;
import lk.ijse.MobiCity.util.Routes;
import java.io.IOException;
import java.sql.SQLException;

public class LogInFormController {
    public AnchorPane mainPane;
    public Pane subPane;
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public Button btnLog;
    public Hyperlink linkNewAccount;

    private UserBOImpl userBO=(UserBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void LogInOnAction(ActionEvent actionEvent) {
        String username=txtUsername.getText();
        try {
            if(txtUsername==null || txtPassword==null){
                txtUsername.setFocusColor(Paint.valueOf("red"));
                txtUsername.requestFocus();
                txtPassword.setFocusColor(Paint.valueOf("red"));
                txtPassword.requestFocus();
            }else {
                String role = userBO.checkRank(username);
                if(role==null){
                    txtUsername.setFocusColor(Paint.valueOf("red"));
                    txtUsername.requestFocus();
                }
                else if (role.equals("Admin")) {
                    String user = userBO.checkLogIn(username);
                    if (user == null) {
                        new Alert(Alert.AlertType.WARNING, "Invalid Username!").show();
                        txtUsername.setFocusColor(Paint.valueOf("red"));
                        txtUsername.requestFocus();
                    } else {
                        if (user.equals(txtPassword.getText())) {
                            Navigation.navigate(Routes.ADMINDASHBOARD, mainPane);
                        } else {
                            txtPassword.setFocusColor(Paint.valueOf("red"));
                            txtPassword.requestFocus();
                        }
                    }
                } else if (role.equals("User")) {
                    String userPassword = userBO.checkLogIn(username);
                    if (userPassword == null) {
                        new Alert(Alert.AlertType.WARNING, "Invalid Username!").show();
                        txtUsername.setFocusColor(Paint.valueOf("red"));
                        txtUsername.requestFocus();
                    } else {
                        if (userPassword.equals(txtPassword.getText())) {
                            Navigation.navigate(Routes.USERDASHBOARD, mainPane);
                        } else {
                            txtPassword.setFocusColor(Paint.valueOf("red"));
                            txtPassword.requestFocus();
                        }
                    }
                } else {
                    txtPassword.setFocusColor(Paint.valueOf("red"));
                    txtPassword.requestFocus();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void linkNewAccountOnAction(ActionEvent actionEvent) {
        String username=txtUsername.getText();
        try {
            String role =userBO.checkRank(username);
            if(role.equals("Admin")){
                Navigation.navigate(Routes.ADDEMPLOYEE,mainPane);
            }else{
                new Alert(Alert.AlertType.WARNING,"Only Admin can!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
