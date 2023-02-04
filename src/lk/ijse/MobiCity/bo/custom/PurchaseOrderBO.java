package lk.ijse.MobiCity.bo.custom;

import lk.ijse.MobiCity.bo.SuperBO;
import lk.ijse.MobiCity.dto.*;
import lk.ijse.MobiCity.entity.Orders;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO {
    boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;
    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;
    ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
    ArrayList<String> loadCustomerId() throws SQLException, ClassNotFoundException;
    ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException;
    boolean savePlaceOrder(PlaceOrderDTO dto) throws SQLException, ClassNotFoundException;
    boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;
}
