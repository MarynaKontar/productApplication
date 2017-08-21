package com.app.endpoints;

import com.app.entities.Manufacturer;
import com.app.entities.Product;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 17.08.2017.
 */
@RestController
@RequestMapping("/product")
public class ProductEndpoints {


    private final ProductService productService;

    @Autowired
    public ProductEndpoints(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String listAllProducts(){
        return productService.findAll().stream().map(Product::toString).collect(Collectors.joining(";"));

    }

    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Product> createProduct(@NotEmpty String name,
                                                 @NotEmpty BigDecimal cost,
                                                 @RequestParam(required = false) Timestamp finalStorageDate,
                                                 @RequestParam(required = false) Manufacturer manufacturer,
                                                 @RequestHeader(value = "header", required = false) String header,
                                                 @CookieValue(value = "y", required = false) String y) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setCost(cost);
        product.setFinalStorageDate(finalStorageDate);
        product.setManufacturer(manufacturer);
        productService.save(product);
        return ResponseEntity.ok(product);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
    }

}
