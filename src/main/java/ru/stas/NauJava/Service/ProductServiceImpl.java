package ru.stas.NauJava.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.stas.NauJava.Dao.ProductRepository;
import ru.stas.NauJava.Entity.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class ProductServiceImpl  implements ProductService{

    private final ProductRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public void createProduct(Long id, String name, double calories) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setCalories(calories);
        userRepository.create(product);
    }

    @Override
    public Product findById(Long id) {
        return userRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void addDateOfEating(Long id) {
        var product = userRepository.read(id);
        product.addDateOfEating(new Date());
        userRepository.update(product);
    }

    @Override
    public List<Date> findAllProductsDateEatingInRange(List<Long> ids, Date from, Date to) {
        var listDates = new ArrayList<Date>();
        for (Long id : ids) {
            var product = userRepository.read(id);
            if ( product != null ) {
                var dateProductInRange = product.getDatesOfEating().filter(x -> x.after(from) && x.before(to));
                listDates.addAll(dateProductInRange.collect(Collectors.toCollection(ArrayList::new)));
            }
        }
        return listDates;
    }
}
