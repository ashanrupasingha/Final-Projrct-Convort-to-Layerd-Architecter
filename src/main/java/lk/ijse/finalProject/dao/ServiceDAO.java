package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.model.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDAO extends CrudDAO<Service>{
    public  List<String> getName() throws SQLException, ClassNotFoundException;
}
