package ru.stas.NauJava.Dao;


import ru.stas.NauJava.Entity.Product;

public interface CrudRepository<T, Id>
{
    void create(T entity);
    T read(Id id);
    void update(T entity);
    void delete(Id id);
}
