package ru.stas.NauJava.CustomRepository;

import ru.stas.NauJava.Entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findByNameAndPriceBetween(String name, Double lowerLimit, Double upperLimit);

    List<Product> findByNameSubstances(String substanceName);
}
