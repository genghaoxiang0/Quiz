package com.ghx.service.impl;

import com.ghx.dao.CategoryDao;
import com.ghx.entity.Category;
import com.ghx.service.CategoryService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@NoArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    @Qualifier("categoryDaoHibernateImpl")
    private CategoryDao categoryDaoHibernate;

    @Override
    @Transactional
    public List<Category> getAllCategory() {
        return categoryDaoHibernate.getAllCategory();
    }
}
