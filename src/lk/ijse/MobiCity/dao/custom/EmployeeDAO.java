package lk.ijse.MobiCity.dao.custom;

import lk.ijse.MobiCity.dao.CrudDAO;
import lk.ijse.MobiCity.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee,String > {
    int checkActiveEmployee() throws SQLException, ClassNotFoundException;
}
