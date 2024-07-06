package lk.ijse.finalProject.controller.impl;

import lk.ijse.finalProject.dao.ServiceDAOimpl;
import lk.ijse.finalProject.dao.SupplierDAOimpl;
import lk.ijse.finalProject.dao.impl.ServiceDAO;
import lk.ijse.finalProject.dao.impl.SupplierDAO;
import lk.ijse.finalProject.model.DTO.CustomerDTO;
import lk.ijse.finalProject.model.DTO.ServiceDTO;
import lk.ijse.finalProject.model.DTO.SupplierDTO;
import lk.ijse.finalProject.model.Service;
import lk.ijse.finalProject.model.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBoimpl implements SupplierBO {
    static SupplierDAO supplierDAO = new SupplierDAOimpl();

    @Override
    public boolean updateCustomer(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupplier_id(),supplierDTO.getSupplier_name(),supplierDTO.getSupplier_contact_number()));
    }

    @Override
    public boolean saveCustomer(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplierDTO.getSupplier_id(),supplierDTO.getSupplier_name(),supplierDTO.getSupplier_contact_number()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public SupplierDTO searchById(String newValue) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.searchById(newValue);
        return new SupplierDTO(supplier.getSupplier_id(),supplier.getSupplier_name(),supplier.getSupplier_contact_number());
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getCurrentId();
    }

    @Override
    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Supplier> vehicles = supplierDAO.getAll();
        List<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier supplier : vehicles) {
            supplierDTOS.add(new SupplierDTO(supplier.getSupplier_id(),supplier.getSupplier_name(),supplier.getSupplier_contact_number()));
        }
        return supplierDTOS;
    }
}
