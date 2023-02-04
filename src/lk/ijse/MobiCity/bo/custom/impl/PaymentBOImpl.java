package lk.ijse.MobiCity.bo.custom.impl;

import lk.ijse.MobiCity.bo.custom.PaymentBO;
import lk.ijse.MobiCity.dao.DAOFactory;
import lk.ijse.MobiCity.dao.custom.PaymentDAO;
import lk.ijse.MobiCity.dto.PaymentDTO;
import lk.ijse.MobiCity.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    private PaymentDAO paymentDAO=(PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> all = paymentDAO.getAll();
        ArrayList<PaymentDTO>allPayment=new ArrayList<>();
        for (Payment payment : all) {
            allPayment.add(new PaymentDTO(payment.getOrderId(), payment.getCusId(), payment.getBilledDate(), payment.getTotal(), payment.getPayment(), payment.getBalance()));
        }
        return allPayment;
    }
    @Override
    public double getTotalIncome() throws SQLException, ClassNotFoundException {
        return paymentDAO.getTotalInCome();
    }
    @Override
    public double getTodayIncome() throws SQLException, ClassNotFoundException {
        return paymentDAO.getTodayInCome();
    }

}
