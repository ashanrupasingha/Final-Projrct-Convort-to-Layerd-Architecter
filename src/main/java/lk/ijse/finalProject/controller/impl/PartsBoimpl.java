package lk.ijse.finalProject.controller.impl;

import lk.ijse.finalProject.dao.PartDAOimpl;
import lk.ijse.finalProject.dao.impl.PartDAO;
import lk.ijse.finalProject.model.DTO.OrderDTO;
import lk.ijse.finalProject.model.DTO.PartDTO;
import lk.ijse.finalProject.model.Order;
import lk.ijse.finalProject.model.Part;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartsBoimpl implements PartsBO{
    static PartDAO partDAO = new PartDAOimpl();


    @Override
    public boolean updateCustomer(PartDTO partDTO) throws SQLException, ClassNotFoundException {
        return partDAO.update(new Part(partDTO.getPart_id(),partDTO.getPart_name(),partDTO.getPart_qyt(),partDTO.getPrice(),partDTO.getSupplier_id()));
    }

    @Override
    public boolean saveCustomer(PartDTO partDTO) throws SQLException, ClassNotFoundException {
        return partDAO.save(new Part(partDTO.getPart_id(),partDTO.getPart_name(),partDTO.getPart_qyt(),partDTO.getPrice(),partDTO.getSupplier_id()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return partDAO.delete(id);
    }

    @Override
    public PartDTO searchById(String newValue) throws SQLException, ClassNotFoundException {
        Part part = partDAO.searchById(newValue);
        return new PartDTO(part.getPart_id(),part.getPart_name(),part.getPart_qyt(),part.getPrice(),part.getSupplier_id());
    }

    @Override
    public List<PartDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Part> orders = partDAO.getAll();
        List<PartDTO> partDTOS = new ArrayList<>();
        for (Part part : orders) {
          partDTOS.add(new PartDTO(part.getPart_id(),part.getPart_name(),part.getPart_qyt(),part.getPrice(),part.getSupplier_id()));
        }
        return partDTOS;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return partDAO.getCurrentId();
    }
}

