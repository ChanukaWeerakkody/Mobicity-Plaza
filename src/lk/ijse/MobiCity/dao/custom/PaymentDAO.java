package lk.ijse.MobiCity.dao.custom;

import lk.ijse.MobiCity.dao.CrudDAO;
import lk.ijse.MobiCity.dto.PaymentDTO;
import lk.ijse.MobiCity.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment,String> {
    double getTodayInCome() throws SQLException, ClassNotFoundException;
    double getTotalInCome() throws SQLException, ClassNotFoundException;
}
