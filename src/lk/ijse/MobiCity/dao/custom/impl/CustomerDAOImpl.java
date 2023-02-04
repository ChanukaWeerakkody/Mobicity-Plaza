package lk.ijse.MobiCity.dao.custom.impl;

import lk.ijse.MobiCity.dao.custom.CustomerDAO;
import lk.ijse.MobiCity.dto.CustomerDTO;
import lk.ijse.MobiCity.entity.Customer;
import lk.ijse.MobiCity.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM customer");
        ArrayList<Customer> allCustomer=new ArrayList<>();
        while (rst.next()){
            allCustomer.add(new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getInt(4)));
        }
        return allCustomer;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO customer (cusId,name, address,contact) VALUES (?,?,?,?)",dto.getCusId(),dto.getName(),dto.getAddress(),dto.getContact());
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE customer SET name=?, address=?,contact=? WHERE cusId=?",dto.getName(),dto.getAddress(),dto.getContact(),dto.getCusId());
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM customer WHERE cusId = ?", s);
        if(rst.next()){
            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4));
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT cusId FROM customer WHERE cusId=?", s).next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM customer WHERE cusId=?",s);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT cusId FROM customer");
        ArrayList<String> codeList = new ArrayList<>();
        while (rst.next()) {
            codeList.add(rst.getString(1));
        }
        return codeList;
    }
}
