package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.model.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T>{
    boolean update(T dto) throws SQLException, ClassNotFoundException;

    boolean save(T dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    T searchById(String newValue) throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;

    List<T> getAll() throws SQLException, ClassNotFoundException;
}
