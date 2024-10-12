package ru.stas.NauJava.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.stas.NauJava.Entity.Product;
import ru.stas.NauJava.Entity.User;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {
}
