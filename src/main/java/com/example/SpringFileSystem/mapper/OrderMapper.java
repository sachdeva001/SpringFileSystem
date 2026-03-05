package com.example.SpringFileSystem.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.SpringFileSystem.dto.OrderRequest;
import com.example.SpringFileSystem.entity.Order;

@Mapper(componentModel = "spring" , uses = OrderItemMapper.class)
public interface OrderMapper {
//
//    // SINGLE object mapping (important)
//    @Mapping(target = "orderId", ignore = true)
//    Order toEntity(OrderRequest dto);
//
//    OrderRequest toDto(Order order);
//
//    // LIST mapping (MapStruct auto generates)
//    List<OrderRequest> toDtoList(List<Order> orders);
//
//    List<Order> toEntityList(List<OrderRequest> dtos);
//
//    // Set parent reference for JPA
//    @AfterMapping
//    default void setOrderReference(@MappingTarget Order order) {
//        if (order.getItems() != null) {
//            order.getItems().forEach(item -> item.setOrder(order));
//        }
//    }
 }
