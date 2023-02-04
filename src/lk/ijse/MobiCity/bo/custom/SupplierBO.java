package lk.ijse.MobiCity.bo.custom;

import lk.ijse.MobiCity.bo.SuperBO;
import lk.ijse.MobiCity.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;
    void saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;
    boolean existsSupplier(String id) throws SQLException, ClassNotFoundException;
    SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    void updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;
}
