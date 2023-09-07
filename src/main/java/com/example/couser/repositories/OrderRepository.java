package com.example.couser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.couser.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
