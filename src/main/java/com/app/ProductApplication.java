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

            Product product = new Product();
            product.setName("apple");
            product.setCost(BigDecimal.valueOf(15));
            product.setFinalStorageDate(new Timestamp(117,11,15,11,59,59,10));
            Manufacturer manufacturer = new Manufacturer("Farm", "some selo", "333 55 78");
            product.setManufacturer(manufacturer);

//            service.save(product);
            product.setCost(BigDecimal.valueOf(20));
//            service.save(product);
            product.setFinalStorageDate(new Timestamp(118,2,10,11,59,59,10));
            service.save(product);
            product.setName("pineapple");
            service.save(product);
            logger.info("New products inserted!");

            List<Product> all = service.findAll();
            logger.info("Products are {}", all);

            List<Product> result = service.findByNameLike("%apple%");
            logger.info("Products are {}", all);

//            System.out.println(service.findOne(1L));
//            System.out.println(service.findOne(2L));
//            System.out.println(service.getOne(3L));
//            System.out.println(service.getOne(4L));
        }
    }
}
