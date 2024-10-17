package ru.stas.NauJava.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.stas.NauJava.Entity.Product;
import ru.stas.NauJava.Entity.Targets;

import java.lang.annotation.Target;

@RepositoryRestResource(path = "targets")
public interface TargetsRepository extends CrudRepository<Targets, Long> {
}
