package ru.stas.NauJava.Service;

import ru.stas.NauJava.Entity.Product;

import java.util.Date;
import java.util.List;

public interface ProductService {
    void createProduct(Long id, String name, double calories);
    Product findById(Long id);
    void deleteById(Long id);
    void addDateOfEating(Long id);
    List<Date> findAllProductsDateEatingInRange(List<Long> ids, Date from, Date to);
}
