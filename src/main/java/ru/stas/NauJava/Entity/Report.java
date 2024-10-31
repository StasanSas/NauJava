package ru.stas.NauJava.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private StatusReport status;
    @Column(length = 10000) // Или укажите нужный вам
    private String content;

    @Column
    private Long amountUsers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reports_products",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    @Column
    private Long timeOfCalcAmountUsers;
    @Column
    private Long timeOfFoundProducts;
    @Column
    private Long timeOfFormationReport;


    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setStatus(StatusReport status) { this.status = status; }
    public StatusReport getStatus() { return status; }

    public void setContent() {
        if (status != StatusReport.COMPLETED){
            this.content = "";
            return;
        }
        this.content = "Количество пользователей:" + amountUsers + "\n";
        this.content += "Время вычисления кол-ва пользователей: " + timeOfCalcAmountUsers + "\n";
        this.content += "Время поиска продуктов: " + timeOfFoundProducts + "\n";
        this.content += "Время составления отчёта: " + timeOfFormationReport + "\n";
        this.content += "Продукты в приложении\n";

        StringBuilder contentBuilder = new StringBuilder();
        for (Product p : products) {
            contentBuilder.append(p.toString());
        }
        this.content += contentBuilder.toString();
    }
    public String getContent() { return content; }

    public void setAmountUsers(Long amountUsers) { this.amountUsers = amountUsers; }
    public Long getAmountUsers() { return amountUsers; }

    public void setProducts(Set<Product> products) { this.products = products; }
    public Set<Product> getProducts() { return products; }

    public Long getTimeOfCalcAmountUsers() { return timeOfCalcAmountUsers; }
    public void setTimeOfCalcAmountUsers(Long timeOfCalcAmountUsers) {
        this.timeOfCalcAmountUsers = timeOfCalcAmountUsers;
    }

    public Long getTimeOfFoundProducts() { return timeOfFoundProducts; }
    public void setTimeOfFoundProducts(Long timeOfFoundProducts) {
        this.timeOfFoundProducts = timeOfFoundProducts;
    }

    public Long getTimeOfFormationReport() { return timeOfFormationReport; }
    public void setTimeOfFormationReport(Long timeOfFormationReport) {
        this.timeOfFormationReport = timeOfFormationReport;
    }

}
