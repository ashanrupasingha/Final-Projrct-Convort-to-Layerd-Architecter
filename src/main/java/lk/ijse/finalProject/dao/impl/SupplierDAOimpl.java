package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.dao.SQLUtil;
import lk.ijse.finalProject.dao.SupplierDAO;
import lk.ijse.finalProject.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOimpl implements SupplierDAO {

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Supplier SET supplier_id = ?, supplier_name = ?,supplier_contact_number = ? WHERE supplier_id = ?",dto.getSupplier_id(),dto.getSupplier_name(),dto.getSupplier_contact_number(),dto.getSupplier_id());
    }

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Supplier VALUES(?,?,?)",dto.getSupplier_id(),dto.getSupplier_name(),dto.getSupplier_contact_number());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Supplier WHERE supplier_id = ?",id);
    }

    @Override
    public Supplier searchById(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("SELECT * FROM Supplier WHERE supplier_id = ?",newValue + "" );
        rst.next();
        Supplier supplier =new Supplier(newValue + "" ,rst.getString("supplier_name"),rst.getString("supplier_contact_number"));
        return supplier;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT supplier_id FROM Supplier ORDER BY supplier_id DESC LIMIT 1");
        rst.next();
        return rst.getString("supplier_id");
    }

    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier");
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (rst.next()) {
            suppliers.add(new Supplier(rst.getString("supplier_id"),rst.getString("supplier_name"),rst.getString("supplier_contact_number")));
        }
        return suppliers;
    }
}
