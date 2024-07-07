package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.dao.SQLUtil;
import lk.ijse.finalProject.dao.ServiceDAO;
import lk.ijse.finalProject.model.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOimpl implements ServiceDAO {
    @Override
    public boolean update(Service dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Service SET service_id = ?, service_package = ?,service_amount = ? WHERE service_id = ?",dto.getService_id(),dto.getService_package(),dto.getService_amount());
    }

    @Override
    public boolean save(Service dto) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("INSERT INTO Service VALUES(?,?,?)",dto.getService_id(),dto.getService_package(),dto.getService_amount());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM Service WHERE service_id = ?",id);
    }

    @Override
    public Service searchById(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("SELECT * FROM Service WHERE service_id = ?",newValue + "" );
        rst.next();
        Service service =new Service(newValue + "" ,rst.getString("service_package"),rst.getDouble("service_amount"));
        return service;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT service_id FROM Service ORDER BY service_id DESC LIMIT 1");
        rst.next();
        return rst.getString("service_id");
    }

    @Override
    public List<Service> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Service");
        ArrayList<Service> parts = new ArrayList<>();
        while (rst.next()) {
            parts.add(new Service(rst.getString("service_id"),rst.getString("service_package"),rst.getDouble("service_amount")));
        }
        return parts;
    }
}
