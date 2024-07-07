package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.OrderDAO;
import lk.ijse.finalProject.dto.OrderDTO;
import lk.ijse.finalProject.model.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBoimpl implements OrderBo {
    static OrderDAO orderDAO = (OrderDAO) DAOFactory.getObject().getDao(DAOFactory.DAOType.Order);

    @Override
    public boolean updateCustomer(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDAO.update(new Order(orderDTO.getOrder_id(), orderDTO.getOrder_discription(), orderDTO.getCustomer_id(), orderDTO.getOrder_qty(), orderDTO.getVehicle_id()));
    }

    @Override
    public boolean saveCustomer(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(orderDTO.getOrder_id(), orderDTO.getOrder_discription(), orderDTO.getCustomer_id(), orderDTO.getOrder_qty(), orderDTO.getVehicle_id()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public OrderDTO searchById(String newValue) throws SQLException, ClassNotFoundException {
        Order orderDTO = orderDAO.searchById(newValue);
        return new OrderDTO(orderDTO.getOrder_id(), orderDTO.getOrder_discription(), orderDTO.getCustomer_id(), orderDTO.getOrder_qty(), orderDTO.getVehicle_id());
    }

    public List<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Order> orders = orderDAO.getAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
          orderDTOS.add(new OrderDTO(order.getOrder_id(), order.getOrder_discription(), order.getCustomer_id(), order.getOrder_qty(), order.getVehicle_id()));
        }
        return orderDTOS;
    }
}
