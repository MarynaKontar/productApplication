package com.app;

import com.app.configuration.ApplicationConfiguration;
import com.app.entities.Manufacturer;
import com.app.entities.Product;
import com.app.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 10.08.2017.
 */
public class ProductApplication {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ProductApplication.class);

        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                ApplicationConfiguration.class)){
            ProductService service = context.getBean(ProductService.class);
            logger.info("Starting product application!");

//            Product product = new Product();
//            product.setName("appleeeee");
//            product.setCost(BigDecimal.valueOf(15));
//            //TODO 2. почему не срабатывает @Future? записывается в БД product с датой из прошлого
//            product.setFinalStorageDate(new Timestamp(100,11,15,11,59,59,10));
//            Manufacturer manufacturer = new Manufacturer("Farm", "some selo", "333 55 78");
//            product.setManufacturer(manufacturer);
//            service.save(product);
//
//            // TODO 3. делает update,а не добавляет новЫй product. Когда первый раз пишем service.save(product)
//            // объект product становится DETACHED object. У него остается связь с бд
////            product.setCost(BigDecimal.valueOf(20));
////            service.save(product);
//
//            Product product1 = new Product();
//            product1.setName("apple");
//            product1.setCost(BigDecimal.valueOf(25));
//            product1.setFinalStorageDate(new Timestamp(118,2,10,11,59,59,10));
//            product1.setManufacturer(manufacturer);
//            service.save(product1);
//
//            Product product2 = new Product();
//            product2.setName("pineapple");
//            product2.setCost(BigDecimal.valueOf(30));
//            product2.setFinalStorageDate(new Timestamp(119,2,10,11,59,59,10));
//            product2.setManufacturer(manufacturer);
//            service.save(product2);
//
//            logger.info("New products inserted!");

            List<Product> all = service.findAll();
            logger.info("Products are {}", all);

            List<Product> result = service.findByNameLike("%apple%");
            logger.info("Products are {}", result);

            System.out.println(service.findOne(4L));
            System.out.println(service.findOne(5L));

            //TODO 4. getOne возврашает proxy, т.е. объект только c id. Другой инф. о product в нем  нет. Используется, если, например, надо user.set(product) - т.е. можно сеттить такой объект
            service.getOne(6L);
//            System.out.println(service.getOne(6L));// не выведет на консоль: выдаст ошибку Exception in thread "main" org.hibernate.LazyInitializationException: could not initialize proxy - no Session
        }
    }
}
