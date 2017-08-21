package com.app.endpoints;

import com.app.entities.Product;
import com.app.entities.User;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    //work +
    @RequestMapping(method = RequestMethod.GET, value = "/show")
    public ModelAndView showProducts() {
        List<String> products =
                productService.findAll()
                        .stream()
                        .map(Product::getName)
                        .collect(Collectors.toList());
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

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {
        // http://localhost:8080/login?logout
        return "redirect:/login?logout";
    }
}
