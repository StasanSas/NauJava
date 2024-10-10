package ru.stas.NauJava.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "substances")
public class Substance {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String info;
    @Column
    private Double lowerLimitOfConcentration;
    @Column
    private Double upperLimitOfConcentration;

    @ManyToMany(mappedBy = "substances")
    private Set<Product> products = new HashSet<>();

    @ManyToMany(mappedBy = "substances")
    private Set<Targets> targets = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }

    public Double getLowerLimitOfConcentration() { return lowerLimitOfConcentration; }
    public void setLowerLimitOfConcentration(Double lowerLimitOfConcentration) {
        this.lowerLimitOfConcentration = lowerLimitOfConcentration;
    }

    public Double getUpperLimitOfConcentration() { return upperLimitOfConcentration; }
    public void setUpperLimitOfConcentration(Double upperLimitOfConcentration) {
        this.upperLimitOfConcentration = upperLimitOfConcentration;
    }

    public Set<Product> getProducts() { return products; }
    public void setProducts(Set<Product> products) {  this.products = products; }

    public Set<Targets> getTargets() { return targets; }
    public void setTargets(Set<Targets> targets) {  this.targets = targets; }

}
