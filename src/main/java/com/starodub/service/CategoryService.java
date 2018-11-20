package com.starodub.service;

import com.starodub.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getById(Long id);
}
