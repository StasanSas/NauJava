package ru.stas.NauJava.Service.ReportService.Treads;


import ru.stas.NauJava.Dao.ProductRepository;
import ru.stas.NauJava.Service.ReportService.ResourceTreads.ResourseFoundingProducts;

public class TreadForFoundingProducts extends Thread{
    private final ResourseFoundingProducts resource;
    private final ProductRepository productRepository;

    public TreadForFoundingProducts(ResourseFoundingProducts resource,
                                 ProductRepository productRepository) {
        this.resource = resource;
        this.productRepository = productRepository;
    }

    @Override
    public void run() {
        var startTime = System.currentTimeMillis();
        var products = productRepository.getAll();
        var elapsed = (System.currentTimeMillis() - startTime);
        resource.setTime(elapsed);
        resource.setProducts(products);
    }
}
