package com.example.SpringFileSystem.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SpringFileSystem.dto.OrderRequest;
import com.example.SpringFileSystem.entity.Order;
import com.example.SpringFileSystem.entity.OrderItem;
import com.example.SpringFileSystem.exception.OrderNotFoundException;
import com.example.SpringFileSystem.mapper.OrderMapper;
import com.example.SpringFileSystem.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private OrderMapper mapper;
	
	
    public List<Order> getAll() {
        return repository.findAll();
    }

    public Order getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
	
    public void saveOrders(List<OrderRequest> requests) {
    	
    	 List<Order> orders = requests.stream().map(r -> {

             Order order = new Order();
             order.setOrderId(r.getOrder_id());
             order.setCustomerId(r.getCustomer_id());
             order.setCustomerName(r.getCustomer_name());
             order.setLoyaltyPoints(r.getLoyalty_points());

             List<OrderItem> items = r.getOrder_items().stream().map(i -> {
                 OrderItem item = new OrderItem();
                 item.setProductId(i.getProduct_id());
                 item.setProductName(i.getProduct_name());
                 item.setProductCategory(i.getProduct_category());
                 item.setUnitPrice(i.getUnit_price());
                 item.setOrder(order);
                 return item;
             }).toList();

             order.setItems(items);
             return order;

         }).toList();

         repository.saveAll(orders);
     }

    public List<Order> findItemsByProductName(String productName) {

        List<Order> orders = repository.findByItemsProductName(productName);

        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found for product: " + productName);
        }

        return orders;
    }
	
}
