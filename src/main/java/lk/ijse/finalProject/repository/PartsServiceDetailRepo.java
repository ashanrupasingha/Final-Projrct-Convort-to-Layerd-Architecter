package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.PartsServiceDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartsServiceDetailRepo {
    public static boolean save(PartsServiceDetail partsServiceDetail) throws SQLException {
        String sql = "INSERT INTO Part_service_detail VALUES (?,?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,partsServiceDetail.getServiceId());
        pstm.setObject(2,partsServiceDetail.getPartId());
        return pstm.executeUpdate() > 0;
    }
}
