package ru.stas.NauJava.CustomRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.stas.NauJava.Entity.Product;
import ru.stas.NauJava.Entity.Substance;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom
{
    private final EntityManager entityManager;
    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    @Override
    public List<Product> findByNameAndPriceBetween(String name, Double lowerLimit, Double upperLimit)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        Predicate namePredicate = criteriaBuilder.equal(productRoot.get("name"), name);
        Predicate pricePredicate = criteriaBuilder.between(productRoot.get("price"), lowerLimit, upperLimit);
        Predicate sumPredicate = criteriaBuilder.and(namePredicate, pricePredicate);

        criteriaQuery.select(productRoot).where(sumPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    @Override
    public List<Product> findByNameSubstances(String substanceName)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        Join<Product, Substance> userAndSubstance = productRoot.join("substances", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(userAndSubstance.get("name"), substanceName);

        criteriaQuery.select(productRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


}
