package lk.ijse.MobiCity.dao.custom;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.MobiCity.dao.CrudDAO;
import lk.ijse.MobiCity.dto.OrderDTO;
import lk.ijse.MobiCity.entity.Orders;

public interface OrderDAO extends CrudDAO<Orders,String > {
}
