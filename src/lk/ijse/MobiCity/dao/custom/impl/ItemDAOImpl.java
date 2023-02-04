package lk.ijse.MobiCity.dao.custom.impl;

import lk.ijse.MobiCity.dao.custom.ItemDAO;
import lk.ijse.MobiCity.dto.ItemDTO;
import lk.ijse.MobiCity.entity.Item;
import lk.ijse.MobiCity.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM item");
        ArrayList<Item> allItem=new ArrayList<>();
        while (rst.next()){
            allItem.add(new Item(rst.getString(1),rst.getString(2),rst.getString(3),rst.getInt(4), rst.getDouble(5)));
        }
        return allItem;
    }

    @Override
    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO item (item_id,item_name, brand,qty,price) VALUES (?,?,?,?,?)",dto.getItemId(),dto.getName(),dto.getBrand(),dto.getQty(),dto.getPrice());
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE item SET item_name=?, brand=?,qty=?,price=? WHERE item_id=?",dto.getName(),dto.getBrand(),dto.getQty(),dto.getPrice(),dto.getItemId());
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM item WHERE item_id = ?", s);
        if(rst.next()){
            return new Item(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getDouble(5));
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT item_id FROM item WHERE item_id=?", s).next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM item WHERE item_id=?",s);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT item_id FROM item");

        ArrayList<String> codeList = new ArrayList<>();

        while (rst.next()) {
            codeList.add(rst.getString(1));
        }
        return codeList;
    }
}
