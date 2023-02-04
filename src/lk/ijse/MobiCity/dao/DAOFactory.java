package lk.ijse.MobiCity.dao;

import lk.ijse.MobiCity.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER,PLACEORDER,PAYMENT,EMPLOYEE,SUPPLIER,USER
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case PLACEORDER:
                return new PlaceOrderDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
