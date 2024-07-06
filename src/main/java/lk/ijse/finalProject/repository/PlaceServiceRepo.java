package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.PlaceService;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceServiceRepo {

    public static boolean saveDetails(PlaceService ps) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            System.out.println("Invoke first one");
            boolean isSaved = VehicleDetailRepo.save(ps.getVehicleDetails());
            if (isSaved) {
                System.out.println("Invoke second one");
                boolean saved = PartsServiceDetailRepo.save(ps.getPartsServiceDetail());
                if (saved) {
                    System.out.println("Invoke third one");
                    boolean isUpdated = PartRepo.update(ps.getPartsServiceDetail().getPartId(), ps.getQty());
                    if (isUpdated) {
                        System.out.println("commited");
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e){
            connection.rollback();
            return false;
        }
    }
}
