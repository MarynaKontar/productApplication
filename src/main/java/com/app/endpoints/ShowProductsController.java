package com.app.endpoints;

import com.app.entities.Manufacturer;
import com.app.entities.Product;
import com.app.entities.User;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 17.08.2017.
 */
@Controller
@RequestMapping("/product")
public class ShowProductsController {


    private ProductService productService;

    @Autowired
    public ShowProductsController(ProductService productService) {
        this.productService = productService;
    }

    //work +
    @GetMapping("/showAll")
//    @RequestMapping(method = RequestMethod.GET, value = "/show")
    public ModelAndView showProducts() {
        List<Product> products =
                productService.findAll();
//                        .stream()
//                        .map(Product::getName)
//                        .collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products");//TODO такое же название как у view (products.jsp)
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    //work +
    @RequestMapping(value = "/find/{id}") //TODO 8.Почему работает и без method = RequestMethod.GET?
    public ModelAndView findProduct(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");//TODO такое же название как у view (product.jsp)
        Product product = productService.findOne(id);
        if (product != null) {
            modelAndView.addObject("product", product);
        }
        return modelAndView;
    }

    @PostMapping(value = "/action/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Product> createProduct(@NotEmpty String name,
                                                 @NotEmpty BigDecimal cost,
                                                 @RequestParam(required = false) Timestamp finalStorageDate,
                                                 @RequestParam(required = false) Manufacturer manufacturer,
                                                 @RequestParam(required = false) String description)
//                                                 @RequestHeader(value = "header", required = false) String header,
//                                                 @CookieValue(value = "y", required = false) String y)
            throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setCost(cost);
        product.setFinalStorageDate(finalStorageDate);
        product.setManufacturer(manufacturer);
        product.setDescription(description);
        productService.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping(value = "/action/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id)
            throws IOException {
        Product product = productService.findOne(id);
        if (product != null) {
        productService.delete(id);}
        return ResponseEntity.ok(product);
    }

}
