package ru.stas.NauJava.Dao;

import org.springframework.data.repository.CrudRepository;
import ru.stas.NauJava.Entity.Product;

public interface TargetsRepository extends CrudRepository<Product, Long> {
}
