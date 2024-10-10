package ru.stas.NauJava.Entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private Double calories;
    @Column
    private Double price;

    @ManyToMany(mappedBy = "products")
    private Set<Meal> meals = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_substances",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "substance_id"))
    private Set<Substance> substances = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getCalories() { return calories; }
    public void setCalories(Double calories) { this.calories = calories; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String toString() {
        return id + " " + name + " " + calories + "\n" ;
    }

    public Set<Meal> getMeals() { return meals; }
    public void setMeals(Set<Meal> meals) { this.meals = meals; }

    public Set<Substance> getSubstance() { return substances; }
    public void setSubstance(Set<Substance> substance) { this.substances = substance; }
}
