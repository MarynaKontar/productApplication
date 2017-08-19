package com.app.endpoints;

import com.app.entities.Product;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 17.08.2017.
 */
@Controller
@RequestMapping("/product")
public class ShowProductsController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, value = "/show")
    public ModelAndView showProducts() {
        List<String> products =
                productService.findAll()
                        .stream()
                        .map(Product::toString)
                        .collect(Collectors.toList());
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("products");
    modelAndView.addObject("products", products);
    return modelAndView;
    }
}
