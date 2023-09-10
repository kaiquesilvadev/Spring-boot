package com.example.couser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.couser.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
