package service;

import entity.Order;
import mapper.OrderMapper;
import mapper.OrderMapperImpl;

import java.util.List;

public class OrderService {
    public OrderMapper orderMapper;

    public OrderService() {
        this.orderMapper=new OrderMapperImpl();
    }

    public int addOrder(Order order){
        return orderMapper.insert(order);
    }

    public List<Order> getByTransactionId(int transaction_id){return orderMapper.selectByTransactionId(transaction_id);}
}
