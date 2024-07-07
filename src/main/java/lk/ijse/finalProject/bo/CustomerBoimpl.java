package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.CustomerDAO;
import lk.ijse.finalProject.model.Customer;
import lk.ijse.finalProject.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoimpl implements CustomerBO{
    static CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getObject().getDao(DAOFactory.DAOType.CUSTOMER);

    public  boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {

        return customerDAO.update(new Customer(customer.getCustomer_id(),customer.getName(),customer.getAddress(),customer.getTelephone()));
    }
    public  boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {

        return customerDAO.save(new Customer(customer.getCustomer_id(),customer.getName(),customer.getAddress(),customer.getTelephone()));
    }
    public  boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {

       return customerDAO.delete(id);
    }


    public CustomerDTO searchById(String newValue) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchById(newValue);
        return new CustomerDTO(customer.getCustomer_id(),customer.getName(),customer.getAddress(),customer.getTelephone());
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return customerDAO.getCurrentId();
    }

    @Override
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Customer> customer = customerDAO.getAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer vehicle: customer){
            customerDTOS.add(new CustomerDTO(vehicle.getCustomer_id(),vehicle.getName(),vehicle.getAddress(),vehicle.getTelephone()));
        }
        return customerDTOS;
    }


}
