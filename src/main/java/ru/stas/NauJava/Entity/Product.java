package ru.stas.NauJava.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

public class Product {

    private Long id;
    private String name;
    private Double calories;
    private final ArrayList<Date> datesOfEating = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getCalories() { return calories; }
    public void setCalories(Double calories) { this.calories = calories; }

    public void addDateOfEating(Date dateOfEating) {
        datesOfEating.add(dateOfEating);
    }

    public Stream<Date> getDatesOfEating() { return datesOfEating.stream(); }

    public String toString() {
        return id + " " + name + " " + calories + "\n" + datesOfEating;
    }
}
