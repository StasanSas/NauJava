package ru.stas.NauJava.Dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.stas.NauJava.Entity.Substance;
import org.springframework.data.repository.CrudRepository;

@RepositoryRestResource
public interface SubstanceRepository extends CrudRepository<Substance, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Substance s WHERE s.name = :name")
    void deleteByName(String name);
}
