package com.app.dao;

import com.app.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Data access object for Data access object for {@link com.app.entities.Product} using JPA Spring DATA.
 */
public interface ProductDao extends JpaRepository<Product, Long> {
    // To use JPA repository you have to extend JpaRepository interface.
    // Не надо писать реализацию этого интерфейса. Не надо писать @Repository Spring сам найдет реализацию JpaRepository (SimpleJpaRepository), там будет стандартный набор
    // crud и еще добавятся те, что мы здесь добавим из доп. списка (конструируется из ключивых слов:
    // findBy(readFirstBy,...) + назвваниеПоляВProduct + Before(Contains,False, Is,...)) и те, которые пропишем через Query

    List<Product> findByFinalStorageDateBefore(Timestamp date);

    //TODO 1 порядок переменных должен соответствовать порядку в названии?
    List<Product> findByNameIsAndManufacturer_NameOrderByCost(String productName, String manufacturerName);


    // Custom search can be added here too.
    // Также можно добавить функцию, которая будет использовать persistent query language (jpql)
    @Query("select product from Product product where product.name like ?1")
    List<Product> findByNameLike(String name);
}
