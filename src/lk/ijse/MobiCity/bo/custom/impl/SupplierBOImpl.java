package lk.ijse.MobiCity.bo.custom.impl;

import lk.ijse.MobiCity.bo.custom.SupplierBO;
import lk.ijse.MobiCity.dao.DAOFactory;
import lk.ijse.MobiCity.dao.custom.SupplierDAO;
import lk.ijse.MobiCity.dto.SupplierDTO;
import lk.ijse.MobiCity.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    private SupplierDAO supplierDAO=(SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> all = supplierDAO.getAll();
        ArrayList<SupplierDTO> allSupplier=new ArrayList<>();
        for (Supplier supplier : all) {
            allSupplier.add(new SupplierDTO(supplier.getSupId(),supplier.getName(),supplier.getItemName(),supplier.getBrand(),supplier.getQty(),supplier.getPrice()));
        }
        return allSupplier;
    }
    @Override
    public void saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        supplierDAO.save(new Supplier(dto.getSupId(), dto.getName(), dto.getItemName(), dto.getBrand(), dto.getQty(),dto.getPrice()));
    }
    @Override
    public boolean existsSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.exist(id);
    }
    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier ent = supplierDAO.search(id);
        return new SupplierDTO(ent.getSupId(), ent.getName(), ent.getItemName(), ent.getBrand(), ent.getQty(),ent.getPrice());
    }
    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }
    @Override
    public void updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        supplierDAO.update(new Supplier(dto.getSupId(), dto.getName(), dto.getItemName(), dto.getBrand(), dto.getQty(),dto.getPrice()));
    }
}
