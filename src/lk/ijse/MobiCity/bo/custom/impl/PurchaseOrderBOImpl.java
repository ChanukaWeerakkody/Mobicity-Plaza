package lk.ijse.MobiCity.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.MobiCity.bo.custom.PurchaseOrderBO;
import lk.ijse.MobiCity.dao.DAOFactory;
import lk.ijse.MobiCity.dao.custom.*;
import lk.ijse.MobiCity.db.DBConnection;
import lk.ijse.MobiCity.dto.*;
import lk.ijse.MobiCity.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    private ItemDAO itemDAO=(ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private CustomerDAO customerDAO=(CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private OrderDAO orderDAO=(OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private PlaceOrderDAO placeOrderDAO=(PlaceOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PLACEORDER);
    private PaymentDAO paymentDAO=(PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            if (orderDAO.exist(dto.getoId())) {
                new Alert(Alert.AlertType.ERROR, "Order id is exists!").show();
            }

            connection.setAutoCommit(false);
            boolean save = orderDAO.save(new Orders(dto.getoId(),dto.getDate(),dto.getCusId()));
            if (!save) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            ArrayList<PlaceOrderDTO> orderDetails=new ArrayList<>();
            for (PlaceOrderDTO detail : orderDetails) {
                boolean save1 = placeOrderDAO.save(new PlaceOrder(detail.getCode(),detail.getDescription(),detail.getQty(),detail.getUnitPrice(),detail.getTotal()));
                if (!save1) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                ItemDTO item=new ItemDTO();
                item.setQty(item.getQty() - detail.getQty());

                boolean update = itemDAO.update(new Item(item.getItemId(),item.getName(),item.getBrand(),item.getQty(),item.getPrice()));

                if (!update) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer ent = customerDAO.search(id);
        return new CustomerDTO(ent.getCusId(), ent.getName(), ent.getAddress(), ent.getContact());
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item ent = itemDAO.search(code);
        return new ItemDTO(ent.getItemId(),ent.getName(),ent.getBrand(),ent.getQty(),ent.getPrice());
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    @Override
    public ArrayList<String> loadCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.loadCustomerIds();
    }

    @Override
    public ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException {
        return itemDAO.loadItemCodes();
    }

    @Override
    public boolean savePlaceOrder(PlaceOrderDTO dto) throws SQLException, ClassNotFoundException {
        return placeOrderDAO.save(new PlaceOrder(dto.getCode(),dto.getDescription(),dto.getQty(),dto.getUnitPrice(),dto.getTotal()));
    }

    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getOrderId(),dto.getCusId(),dto.getBilledDate(),dto.getTotal(),dto.getPayment(),dto.getBalance()));
    }
}
