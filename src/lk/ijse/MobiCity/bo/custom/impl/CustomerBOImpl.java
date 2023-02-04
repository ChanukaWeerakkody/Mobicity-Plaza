package lk.ijse.MobiCity.bo.custom.impl;

import lk.ijse.MobiCity.bo.custom.CustomerBO;
import lk.ijse.MobiCity.dao.DAOFactory;
import lk.ijse.MobiCity.dao.custom.CustomerDAO;
import lk.ijse.MobiCity.dto.CustomerDTO;
import lk.ijse.MobiCity.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO=(CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomer=new ArrayList<>();
        for (Customer customer : all) {
            allCustomer.add(new CustomerDTO(customer.getCusId(), customer.getName(), customer.getAddress(), customer.getContact()));
        }
        return allCustomer;
    }
    @Override
    public void saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        customerDAO.save(new Customer(dto.getCusId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }
    @Override
    public boolean existsCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }
    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer ent = customerDAO.search(id);
        return new CustomerDTO(ent.getCusId(), ent.getName(), ent.getAddress(), ent.getContact());
    }
    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }
    @Override
    public void updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        customerDAO.update(new Customer(dto.getCusId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }
}
