package ru.stas.NauJava.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.stas.NauJava.Entity.Product;
import ru.stas.NauJava.Entity.User;

@RepositoryRestResource(path = "user")
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT COUNT(u) FROM User u")
    Long getAmountOfUsers();
}
