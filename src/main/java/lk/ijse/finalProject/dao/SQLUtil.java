package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.db.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql, Object... obj) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        int index = 1;
        for (Object i : obj){
            pstm.setObject(index,i);
            ++index;
        }
        if (sql.startsWith("SELECT")){
            return (T) pstm.executeQuery();
        } else {
            return (T) (Boolean)(pstm.executeUpdate() > 0);
        }
    }
}
