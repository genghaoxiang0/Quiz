package com.ghx.dao.impl;

import com.ghx.dao.AbstractHibernateDAO;
import com.ghx.dao.CategoryDao;
import com.ghx.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoHibernateImpl extends AbstractHibernateDAO implements CategoryDao {
    public CategoryDaoHibernateImpl() {
        setClazz(Category.class);
    }
    @Override
    public List<Category> getAllCategory() {
        return findAll();
    }
}
