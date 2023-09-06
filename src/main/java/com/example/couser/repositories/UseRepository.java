package com.example.couser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.couser.entities.User;

public interface UseRepository extends JpaRepository<User, Long>{

	
}
