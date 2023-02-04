package lk.ijse.MobiCity.dao.custom;

import lk.ijse.MobiCity.dao.CrudDAO;
import lk.ijse.MobiCity.dto.CustomerDTO;
import lk.ijse.MobiCity.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String > {
    ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException;

}
