package ru.stas.NauJava;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.stas.NauJava.Entity.Product;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigDb {
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public Map<Long, Product> productsContainer(){
        return new HashMap<>();
    }
}
