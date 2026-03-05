package com.example.SpringFileSystem.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.SpringFileSystem.dto.OrderRequest;
import com.example.SpringFileSystem.entity.Order;
import com.example.SpringFileSystem.factory.JsonHandler;
import com.example.SpringFileSystem.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OrderJsonHandler implements JsonHandler{

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
    private OrderService orderService;
    
	@Override
	public boolean supports(JsonNode root) {
		return root.get(0).has("date_consumed");
	}

	 @Override
	 public void process(JsonNode root) throws Exception {
	
        List<OrderRequest> orders =
                mapper.readerForListOf(OrderRequest.class)
                      .readValue(root);

        orderService.saveOrders(orders);
	
	 
	 }
	 
	 
}
