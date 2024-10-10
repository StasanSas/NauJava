package ru.stas.NauJava.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private int weight;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Targets> targets = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Meal> meals = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public Set<Targets> getTargets() { return targets; }
    public void setTargets(Set<Targets> targets) { this.targets = targets; }

    public Set<Meal> getMeals() { return meals; }
    public  void setMeals(Set<Meal> meals) { this.meals = meals; }
}
