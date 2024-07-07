package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.EmployeeDAO;
import lk.ijse.finalProject.dto.EmployeeDTO;
import lk.ijse.finalProject.model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoimpl implements EmployeeBo{
    static EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getObject().getDao(DAOFactory.DAOType.Employee);
    @Override
    public boolean updateCustomer(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDTO.getEmployee_id(),employeeDTO.getEmployee_name(),employeeDTO.getAddress(),employeeDTO.getTelephone()));
    }

    @Override
    public boolean saveCustomer(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employeeDTO.getEmployee_id(),employeeDTO.getEmployee_name(),employeeDTO.getAddress(),employeeDTO.getTelephone()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO searchById(String newValue) throws SQLException, ClassNotFoundException {
      Employee employeeDTO = employeeDAO.searchById(newValue);
      return new EmployeeDTO(employeeDTO.getEmployee_id(),employeeDTO.getEmployee_name(),employeeDTO.getAddress(),employeeDTO.getTelephone());
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getCurrentId();
    }

    @Override
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Employee> employee = employeeDAO.getAll();
        List<EmployeeDTO> EmployeeS = new ArrayList<>();
        for (Employee employee1 : employee) {
             EmployeeS.add(new EmployeeDTO(employee1.getEmployee_id(), employee1.getEmployee_name(), employee1.getAddress(), employee1.getTelephone()));
        }
        return EmployeeS;
    }

}
