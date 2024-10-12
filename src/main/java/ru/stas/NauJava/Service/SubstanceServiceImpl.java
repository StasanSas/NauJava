package ru.stas.NauJava.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.stas.NauJava.Dao.ProductRepository;
import ru.stas.NauJava.Dao.SubstanceRepository;
import ru.stas.NauJava.Entity.Product;

import java.util.List;


@Service
public class SubstanceServiceImpl implements SubstanceService {
    private final SubstanceRepository substanceRepository;
    private final ProductRepository productRepository;
    private final PlatformTransactionManager transactionManager;
    @Autowired
    public SubstanceServiceImpl(PlatformTransactionManager transactionManager,
                                SubstanceRepository substanceRRepository,
                                ProductRepository productRepository)
    {
        this.substanceRepository = substanceRRepository;
        this.productRepository = productRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public void deleteSubstance(String substanceName) {
        TransactionStatus status = transactionManager.getTransaction(new
                DefaultTransactionDefinition());
        try {
            List<Product> products = productRepository.findBySubstancesName(substanceName);

            productRepository.deleteAll(products);
            substanceRepository.deleteByName(substanceName); // Выполняет удаление
            transactionManager.commit(status);
        } catch (DataAccessException ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
