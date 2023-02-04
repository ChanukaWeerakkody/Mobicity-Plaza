package lk.ijse.MobiCity.bo.custom;

import lk.ijse.MobiCity.bo.SuperBO;
import lk.ijse.MobiCity.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException;
    double getTotalIncome() throws SQLException, ClassNotFoundException;
    double getTodayIncome() throws SQLException, ClassNotFoundException;
}
