package lk.ijse.MobiCity.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();
        switch (route){
            case LOG:
                window.setTitle("Log iN");
                initUI("LogInForm.fxml");
                break;
            case ADMINDASHBOARD:
                window.setTitle("DashBoard");
                initUI("AdminDashBoard.fxml");
                break;
            case USERDASHBOARD:
                window.setTitle("Dashboard");
                initUI("UserDashboard.fxml");
                break;
            case EMPLOYEE:
                window.setTitle("Employee");
                initUI("Employee.fxml");
                break;
            case ADDEMPLOYEE:
                window.setTitle("Add Employee");
                initUI("AddEmployee.fxml");
                break;
            case DELETEEMPLOYEE:
                window.setTitle("Delete Employee");
                initUI("DeleteEmployee.fxml");
                break;
            case UPDATEEMPLOYEE:
                window.setTitle("Update Employee");
                initUI("UpdateEmployee.fxml");
                break;
            case SEARCHEMPLOYEE:
                window.setTitle("Search Employee");
                initUI("SearchEmployee.fxml");
                break;
            case USER:
                window.setTitle("Manage User");
                initUI("UserForm.fxml");
                break;
            case SEARCHUSER:
                window.setTitle("Search User");
                initUI("SearchUser.fxml");
                break;
            case UPDATEUSER:
                window.setTitle("Update User");
                initUI("UpdateUser.fxml");
                break;
            case DELETEUSER:
                window.setTitle("Delete User");
                initUI("DeleteUser.fxml");
                break;
            case PLACEORDER:
                window.setTitle("Place Order Form");
                initUI("PurchaseOrder.fxml");
                break;
            case CUSTOMER:
                window.setTitle("Customer Form");
                initUI("Customer.fxml");
                break;
            case DELETECUSTOMER:
                window.setTitle("Customer Delete Form");
                initUI("DeleteCustomer.fxml");
                break;
            case UPDATECUSTOMER:
                window.setTitle("Customer Update Form");
                initUI("UpdateCustomer.fxml");
                break;
            case SEARCHCUSTOMER:
                window.setTitle("Customer Search Form");
                initUI("SearchCustomer.fxml");
                break;
            case SUPPLIER:
                window.setTitle("Supplier Form");
                initUI("Supplier.fxml");
                break;
            case UPDATESUPPLIER:
                window.setTitle("Supplier Update Form");
                initUI("UpdateSupplier.fxml");
                break;
            case SEARCHSUPPLIER:
                window.setTitle("Supplier Search Form");
                initUI("SearchSupplier.fxml");
                break;
            case DELETESUPPLIER:
                window.setTitle("Supplier Delete Form");
                initUI("DeleteSupplier.fxml");
                break;
            case ORDER:
                window.setTitle("Order Form");
                initUI("OrderForm.fxml");
                break;
            case ITEM:
                window.setTitle("Item Form");
                initUI("Item.fxml");
                break;
            case DELETEITEM:
                window.setTitle("Item delete Form");
                initUI("DeleteItem.fxml");
                break;
            case SEARCHITEM:
                window.setTitle("Item search Form");
                initUI("SearchItem.fxml");
                break;
            case UPDATEITEM:
                window.setTitle("Item update Form");
                initUI("UpdateItem.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }

    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/MobiCity/view/" + location)));
    }
}
