package ru.stas.NauJava.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stas.NauJava.Entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameAndPriceBetween(String name, Double lowerLimit, Double upperLimit);

    @Query("SELECT p FROM Product p JOIN p.substances s WHERE s.name = :substanceName")
    List<Product> findByNameSubstances(String substanceName);

    void delete(Product product);
}
