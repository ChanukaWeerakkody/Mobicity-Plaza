package lk.ijse.MobiCity.dao.custom;

import lk.ijse.MobiCity.dao.CrudDAO;
import lk.ijse.MobiCity.dto.ItemDTO;
import lk.ijse.MobiCity.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item,String> {
    ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException;
}
