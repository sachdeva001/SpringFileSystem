package com.example.SpringFileSystem.dto;

import java.util.List;

public class OrderRequest {

    private Long order_id;
    private Long customer_id;
    private String customer_name;
    private Integer loyalty_points;
    
    private List<OrderItemRequest> order_items;
    
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public Integer getLoyalty_points() {
		return loyalty_points;
	}
	public void setLoyalty_points(Integer loyalty_points) {
		this.loyalty_points = loyalty_points;
	}
	public List<OrderItemRequest> getOrder_items() {
		return order_items;
	}
	public void setOrder_items(List<OrderItemRequest> order_items) {
		this.order_items = order_items;
	}
	public OrderRequest(Long order_id, Long customer_id, String customer_name, Integer loyalty_points,
			List<OrderItemRequest> order_items) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.loyalty_points = loyalty_points;
		this.order_items = order_items;
	}
	public OrderRequest() {
		// TODO Auto-generated constructor stub
	}
    
    
    
}
