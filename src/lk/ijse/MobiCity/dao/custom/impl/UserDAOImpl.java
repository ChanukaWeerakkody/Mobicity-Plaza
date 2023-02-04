package lk.ijse.MobiCity.dao.custom.impl;

import lk.ijse.MobiCity.dao.custom.UserDAO;
import lk.ijse.MobiCity.dto.UserDTO;
import lk.ijse.MobiCity.entity.User;
import lk.ijse.MobiCity.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM user");
        ArrayList<User> allUser=new ArrayList<>();
        while (rst.next()){
            allUser.add(new User(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return allUser;
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO user (emp_id,system_role, username,password) VALUES (?,?,?,?)",dto.getEmpId(),dto.getSystemRole(),dto.getUsername(),dto.getPassword());
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE user SET system_role=?, username=?,password=? WHERE emp_id=?",dto.getSystemRole(),dto.getUsername(),dto.getPassword(),dto.getEmpId());
    }

    @Override
    public User search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM user WHERE emp_id = ?", s);
        if(rst.next()){
            return new User(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT emp_id FROM user WHERE emp_id=?", s).next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM user WHERE emp_id=?",s);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT emp_id FROM user ORDER BY emp_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int rId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", id);
        } else {
            return "E00-001";
        }
    }

    @Override
    public String checkLogIn(String username) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select password from user where username=?", username);
        if (rst.next()){
            return  rst.getString(1);
        }
        return null;
    }

    @Override
    public String checkRank(String username) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select system_role from user where username=?", username);
        if (rst.next()){
            return  rst.getString(1);
        }
        return null;
    }
}
