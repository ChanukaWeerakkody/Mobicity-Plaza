package lk.ijse.MobiCity.dao.custom.impl;

import lk.ijse.MobiCity.dao.custom.QueryDAO;
import lk.ijse.MobiCity.dto.CustomDTO;
import lk.ijse.MobiCity.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomDTO> searchOrderByOrderId(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("Select Orders.oid,Orders.date,Orders.customerID,OrderDetails.itemCode,OrderDetails.qty,OrderDetails.unitPrice from Orders inner join OrderDetails on Orders.oid=OrderDetails.oid where Orders.oid=?;", id);
        ArrayList<CustomDTO> orderRecords = new ArrayList();

        while(rst.next()){
            orderRecords.add(new CustomDTO(rst.getString(1), LocalDate.parse(rst.getString(2)),rst.getString(3),rst.getString(4),rst.getInt(5),rst.getBigDecimal(6)));
        }
        return orderRecords;
    }
}
