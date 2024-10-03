package ru.stas.NauJava.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stas.NauJava.Entity.Product;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepository implements CrudRepository<Product,Long> {


    private final Map<Long,Product> productContainer;

    @Autowired
    public ProductRepository(Map<Long, Product> productContainer)
    {
        this.productContainer = productContainer;
    }

    @Override
    public void create(Product product) {
        productContainer.put(product.getId(), product);
    }

    @Override
    public Product read(Long id) {
        if (productContainer.containsKey(id)){
            return productContainer.get(id);
        }
        return null;
    }

    @Override
    public void update(Product product) {
        productContainer.put(product.getId(), product);
    }

    @Override
    public void delete(Long aLong) {
        productContainer.remove(aLong);
    }
}
