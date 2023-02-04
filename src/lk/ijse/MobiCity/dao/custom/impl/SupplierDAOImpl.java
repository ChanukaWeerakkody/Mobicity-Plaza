package lk.ijse.MobiCity.dao.custom.impl;

import lk.ijse.MobiCity.dao.custom.SupplierDAO;
import lk.ijse.MobiCity.dto.SupplierDTO;
import lk.ijse.MobiCity.entity.Supplier;
import lk.ijse.MobiCity.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM supplier");
        ArrayList<Supplier> allSupplier=new ArrayList<>();
        while (rst.next()){
            allSupplier.add(new Supplier(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4), rst.getInt(5), rst.getDouble(6)));
        }
        return allSupplier;
    }

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO supplier (sup_id,sup_name, item_name,brand,qty,price) VALUES (?,?,?,?,?,?)",dto.getSupId(),dto.getName(),dto.getItemName(),dto.getBrand(),dto.getQty(),dto.getPrice());
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE supplier SET sup_name=?, item_name=?,brand=?,qty=?,price=? WHERE sup_id=?",dto.getName(),dto.getItemName(),dto.getBrand(),dto.getQty(),dto.getPrice(),dto.getSupId());
    }

    @Override
    public Supplier search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM supplier WHERE sup_id = ?", s);
        if(rst.next()){
            return new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getDouble(6));
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT sup_id FROM supplier WHERE sup_id=?", s).next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM supplier WHERE sup_id=?",s);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
