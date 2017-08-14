package com.app.service;

import com.app.dao.ProductDao;
import com.app.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 09.08.2017.
 */
// Service for holding information about products must be created.
@Service
public class ProductService {

    private final ProductDao dao;

    // JPA implementation of ProductDao will be automatically injected here.
    @Autowired
    public ProductService(ProductDao dao) {
        this.dao = dao;
    }

    // All methods that will be used transaction / DB connections must be annotated by @Transaction annotation.

@Transactional(readOnly = true)
    public List<Product> findByFinalStorageDateBefore(Timestamp date) {
        return dao.findByFinalStorageDateBefore(date);
    }

    /**
     * Transactional annotation will be handled by {@link org.springframework.transaction.interceptor.TransactionInterceptor}
     * and new Session (in case hibernate is using) will be set into SessionFactory.currentSession
     * of Entity manager when JPA is used.
     */
    @Transactional(readOnly = true)
    public List<Product> findByNameIsAndManufacturer_NameOrderByCost(String productName, String manufacturerName) {
        return dao.findByNameIsAndManufacturer_NameOrderByCost(productName, manufacturerName);
    }

    @Transactional(readOnly = true)
    public List<Product> findByNameLike(String name) {
        return dao.findByNameLike(name);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return dao.findAll();
    }

    @Transactional
    public Product save(Product entity) {
        return dao.save(entity);
    }

    @Transactional(readOnly = true)
    public Product findOne(Long id) {
        return dao.findOne(id);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long id) {
        return dao.exists(id);
    }

    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }

    @Transactional
    public void delete(Product entity) {
        dao.delete(entity);
    }

    @Transactional(readOnly = true)
    public Product getOne(Long id) {
        return dao.getOne(id);
    }

    public <S extends Product> List<S> save(Iterable<S> entities) {
        return dao.save(entities);
    }
}
