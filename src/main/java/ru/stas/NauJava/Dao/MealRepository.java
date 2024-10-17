package ru.stas.NauJava.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.stas.NauJava.Entity.Meal;
import ru.stas.NauJava.Entity.Product;

@RepositoryRestResource(path = "meal")
public interface MealRepository extends CrudRepository<Meal, Long> {
}
