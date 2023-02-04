package lk.ijse.MobiCity.dao.custom;

import lk.ijse.MobiCity.dao.CrudDAO;
import lk.ijse.MobiCity.dto.UserDTO;
import lk.ijse.MobiCity.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User,String> {

    String checkLogIn(String username) throws SQLException, ClassNotFoundException;
    String checkRank(String username) throws SQLException, ClassNotFoundException;
}
