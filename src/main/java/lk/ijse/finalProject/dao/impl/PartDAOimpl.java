package lk.ijse.finalProject.dao.impl;

import lk.ijse.finalProject.dao.SQLUtil;
import lk.ijse.finalProject.dao.PartDAO;
import lk.ijse.finalProject.model.Part;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartDAOimpl implements PartDAO {


    @Override
    public boolean update(Part dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Part SET  part_name = ?,part_qty = ?,price = ? ,supplier_id = ? WHERE part_id = ?",dto.getPart_name(),dto.getPart_qyt(),dto.getPrice(),dto.getSupplier_id(),dto.getPart_id());
    }

    @Override
    public boolean save(Part dto) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("INSERT INTO Part VALUES(?,?,?,?,?)",dto.getPart_id(),dto.getPart_name(),dto.getPart_qyt(),dto.getPrice(),dto.getSupplier_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM Part WHERE part_id = ?",id);
    }

    @Override
    public Part searchById(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("SELECT * FROM  Part WHERE part_id = ?",newValue +"");
        rst.next();
        Part part =new Part(newValue + "",rst.getString("part_name"),rst.getInt("part_qty"),rst.getDouble("price"),rst.getString("supplier_id"));
        return part;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT part_id FROM Part ORDER BY part_id DESC LIMIT 1");
        rst.next();
        return rst.getString("part_id");
    }

    @Override
    public List<Part> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Part");
        ArrayList<Part> parts = new ArrayList<>();
        while (rst.next()) {
            parts.add(new Part(rst.getString("part_id"),rst.getString("part_name"),rst.getInt("part_qty"),rst.getDouble("price"),rst.getString("supplier_id")));
        }
        return parts;
    }

    @Override
    public boolean updatePartAfterService(int qty, String partId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Part SET part_qty = part_qty - ? WHERE part_id = ?",qty,partId);
    }

    @Override
    public List<String> getNames() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT part_name FROM Part");
        List<String> idList = new ArrayList<>();
        while (resultSet.next()){
            idList.add(resultSet.getString("part_name"));
        }
        return idList;
    }
}

