package lk.ijse.MobiCity.dao.custom.impl;

import lk.ijse.MobiCity.dao.custom.PlaceOrderDAO;
import lk.ijse.MobiCity.dto.PlaceOrderDTO;
import lk.ijse.MobiCity.entity.PlaceOrder;
import lk.ijse.MobiCity.util.SQLUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {
    @Override
    public ArrayList<PlaceOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(PlaceOrder dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO placeorder (item_id,description, qty,unitPrice,total) VALUES (?,?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getQty(),dto.getUnitPrice(),dto.getTotal());
    }

    @Override
    public boolean update(PlaceOrder dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PlaceOrder search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
