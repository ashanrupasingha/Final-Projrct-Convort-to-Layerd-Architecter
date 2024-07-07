package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.model.Part;

import java.sql.SQLException;

public interface PartDAO extends CrudDAO<Part>{
    public boolean updatePartAfterService(int qty, String partId) throws SQLException, ClassNotFoundException;
}
