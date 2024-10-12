package ru.stas.NauJava.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stas.NauJava.Dao.ProductRepository;
import ru.stas.NauJava.Entity.Product;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ControllerProduct {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/filter")
    public List<Product> findByNameAndPriceBetween(@RequestParam String name,
                                                   @RequestParam double low,
                                                   @RequestParam double high) {
        return productRepository.findByNameAndPriceBetween(name, low, high);
    }

    @GetMapping("/filter/substance")
    public List<Product> findBySubstancesName(@RequestParam String SubstancesName) {
        return productRepository.findBySubstancesName(SubstancesName);
    }

}
