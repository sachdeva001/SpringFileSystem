package com.example.SpringFileSystem.service;

import com.example.SpringFileSystem.dto.OrderRequest;
import com.example.SpringFileSystem.dto.OrderItemRequest;
import com.example.SpringFileSystem.entity.Order;
import com.example.SpringFileSystem.mapper.OrderMapper;
import com.example.SpringFileSystem.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("OrderService Tests")
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    private Order order1;
    private Order order2;
    private OrderRequest orderRequest1;
    private OrderRequest orderRequest2;
    private List<Order> orders;
    private List<OrderRequest> orderRequests;

    @BeforeEach
    void setUp() {
        order1 = new Order();
        order1.setOrderId(1L);
        order1.setCustomerId(100L);
        order1.setCustomerName("John Doe");
        order1.setLoyaltyPoints(500);

        order2 = new Order();
        order2.setOrderId(2L);
        order2.setCustomerId(101L);
        order2.setCustomerName("Jane Smith");
        order2.setLoyaltyPoints(750);

        orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        orderRequest1 = new OrderRequest();
        orderRequest1.setOrder_id(1L);
        orderRequest1.setCustomer_id(100L);
        orderRequest1.setCustomer_name("John Doe");
        orderRequest1.setLoyalty_points(500);

        orderRequest2 = new OrderRequest();
        orderRequest2.setOrder_id(2L);
        orderRequest2.setCustomer_id(101L);
        orderRequest2.setCustomer_name("Jane Smith");
        orderRequest2.setLoyalty_points(750);

        orderRequests = new ArrayList<>();
        orderRequests.add(orderRequest1);
        orderRequests.add(orderRequest2);
    }

    @Test
    @DisplayName("Should retrieve order by id")
    void testGetById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));

        Order result = orderService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getOrderId());
        assertEquals("John Doe", result.getCustomerName());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when order not found")
    void testGetByIdNotFound() {
        when(orderRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> orderService.getById(999L),
                "Order not found");
        verify(orderRepository, times(1)).findById(999L);
    }








}
