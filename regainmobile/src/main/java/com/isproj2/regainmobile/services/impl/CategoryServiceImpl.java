package com.isproj2.regainmobile.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.CategoryDTO;
import com.isproj2.regainmobile.repo.CategoryRepository;
import com.isproj2.regainmobile.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryDTO(category.getCategoryID(),
                        category.getName()))
                .collect(Collectors.toList());

    }

}
