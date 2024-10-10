package ru.stas.NauJava.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "targets")
public class Targets {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Date fromDate;
    @Column
    private Date toDate;
    @Column
    private int amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "targets_substances",
            joinColumns = @JoinColumn(name = "target_id"),
            inverseJoinColumns = @JoinColumn(name = "substance_id"))
    private Set<Substance> substances = new HashSet<>();


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getFromDate() { return fromDate; }
    public void setFromDate(Date fromDate) { this.fromDate = fromDate; }

    public Date getToDate() { return toDate; }
    public void setToDate(Date toDate) { this.toDate = toDate; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

}
