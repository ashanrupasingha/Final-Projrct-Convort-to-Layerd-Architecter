package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier>{
    public  List<String > getSupplierId() throws SQLException, ClassNotFoundException;

}
