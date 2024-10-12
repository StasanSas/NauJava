package ru.stas.NauJava.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.stas.NauJava.Entity.Product;

import java.util.List;


@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameAndPriceBetween(String name, Double lowerLimit, Double upperLimit);

    @Query("SELECT p FROM Product p JOIN p.substances s WHERE s.name = :substanceName")
    List<Product> findBySubstancesName(String substanceName);

    void delete(Product product);
}
