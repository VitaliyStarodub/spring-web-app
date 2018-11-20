package com.starodub.dao;

import com.starodub.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAll();

    Category getById(Long id);
}
