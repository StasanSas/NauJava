package ru.stas.NauJava.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stas.NauJava.Dao.ProductRepository;

@Controller
@RequestMapping("/products")
public class ControllerView {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/view")
    public String view(Model model) {
        var a = productRepository.findAll();
        model.addAttribute("products", a);
        return "productList";
    }
}
