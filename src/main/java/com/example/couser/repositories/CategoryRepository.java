package com.example.couser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.couser.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
