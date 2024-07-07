package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dao.impl.*;

import static lk.ijse.finalProject.bo.BoFactory.BoType.*;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){
    }
    public static BoFactory getObject(){

        return (boFactory == null) ? new BoFactory() : boFactory;
    }
    public enum BoType{
        CUSTOMER,CustomerVehicle,Employee,Sale,Order,Part,PartsServiceDetail,Service,Supplier,VehicleDetails,Vehicle
    }
    public SuperBo getbo(BoFactory.BoType orderType) {
        switch (orderType) {
            case CUSTOMER:
                return new CustomerBoimpl();
            case CustomerVehicle:
                return new CustomerVehicleBoimpl();
            case Employee:
                return new EmployeeBoimpl();
            case Sale:
                return new SaleBoimpl();
            case Order:
                return new OrderBoimpl();
            case Part:
                return new PartsBoimpl();
            case PartsServiceDetail:
                return new ServicePartBoimpl();

            case Service:
                return new ServiceBoimpl();

            case Supplier:
                return new SupplierBoimpl();

            case VehicleDetails:
                return new VehicleDetailBOImpl();

            case Vehicle:
                return new VehicleBoimpl();

            default:
                return null;
        }
    }
}
