package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dto.PartDTO;

import java.sql.SQLException;
import java.util.List;

public interface PartsBO extends SuperBo{
    boolean updatePart(PartDTO partDTO) throws SQLException, ClassNotFoundException;
    boolean savePart(PartDTO partDTO) throws SQLException, ClassNotFoundException;
    boolean deletePart(String id) throws SQLException, ClassNotFoundException;
    PartDTO searchById(String newValue) throws SQLException, ClassNotFoundException;
    public List<PartDTO> getAll() throws SQLException, ClassNotFoundException;
    public String getCurrentId() throws SQLException, ClassNotFoundException;
    public boolean updatePartAfterService(int qty, String partId) throws SQLException, ClassNotFoundException;
    public List<String> getNames() throws SQLException, ClassNotFoundException;
}
