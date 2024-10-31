package ru.stas.NauJava.Service.ReportService.ResourceTreads;

import ru.stas.NauJava.Entity.Product;

import java.util.List;

public class ResourseFoundingProducts {
    private List<Product> products;
    private Long time;

    public synchronized List<Product> getProducts() {
        return products;
    }
    public synchronized void setProducts(List<Product> products) {
        this.products = products;
    }

    public synchronized Long getTime() { return time; }
    public synchronized void setTime(Long time) { this.time = time; }
}
