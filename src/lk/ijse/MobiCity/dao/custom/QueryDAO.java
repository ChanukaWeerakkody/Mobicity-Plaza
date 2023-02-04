package lk.ijse.MobiCity.dao.custom;

import lk.ijse.MobiCity.dao.SuperDAO;
import lk.ijse.MobiCity.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomDTO> searchOrderByOrderId(String id)throws SQLException,ClassNotFoundException;
}
