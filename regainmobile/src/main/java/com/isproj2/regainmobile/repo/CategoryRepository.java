package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isproj2.regainmobile.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);
}
