package lk.ijse.MobiCity.dao.custom.impl;

import lk.ijse.MobiCity.dao.custom.PaymentDAO;
import lk.ijse.MobiCity.dto.PaymentDTO;
import lk.ijse.MobiCity.entity.Payment;
import lk.ijse.MobiCity.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM payment");
        ArrayList<Payment> allPayment=new ArrayList<>();
        while (rst.next()){
            allPayment.add(new Payment(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4), rst.getDouble(5), rst.getDouble(6) ));
        }
        return allPayment;
    }

    @Override
    public boolean save(Payment dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO payment (oid,cusId, billedDate,total,payment,balance) VALUES (?,?,?,?,?,?)",dto.getOrderId(),dto.getCusId(),dto.getBilledDate(),dto.getTotal(),dto.getPayment(),dto.getBalance());
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Payment search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM payment WHERE oid LIKE ? OR  cusId LIKE ? OR billedDate LIKE ? OR total LIKE ? OR payment LIKE ? OR payment LIKE ?");
        s="%"+s+"%";
        List<Payment> orders=new ArrayList<>();
        while(rst.next()){
            orders.add(new Payment(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4),rst.getDouble(5),rst.getDouble(6)));
        }
        return (Payment) orders;
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

    @Override
    public double getTodayInCome() throws SQLException, ClassNotFoundException {
        Date date=new Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        ResultSet rst = SQLUtil.executeQuery("select SUM(total) FROM payment WHERE billedDate='" + sqlDate + "'");
        if(rst.next()){
            return rst.getDouble(1);
        }
        return 0;
    }

    @Override
    public double getTotalInCome() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select SUM(total) FROM payment");
        if(rst.next()){
            return rst.getDouble(1);
        }
        return 0;
    }
}
