package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getObject(){

        return (daoFactory == null) ? new DAOFactory() : daoFactory;
    }
    public enum DAOType{
        CUSTOMER,CustomerVehicle,Employee,Sale,Order,Part,PartsServiceDetail,Service,Supplier,VehicleDetails,Vehicle
    }
    public SuperDAO getDao(DAOType orderType) {
        switch (orderType) {
            case CUSTOMER:
                return new CustomerDAOimpl();
            case  CustomerVehicle:
                return new CustomerVehicleDAOimpl();
            case Employee:
                return new EmployeeDAOimpl();
            case Sale:
                return new SaleDAOimpl();
            case Order:
                return new OrderDAOimpl();
            case Part:
                return new PartDAOimpl();
            case PartsServiceDetail:
                return new ServicePartDAOimpl();

            case Service:
                return new ServiceDAOimpl();

            case Supplier:
                return new SupplierDAOimpl();

            case VehicleDetails:
                return new VehicleDetailDAOImpl();

            case Vehicle:
                return new VehicleDAOimpl();

            default:
                return null;
        }
    }
}
