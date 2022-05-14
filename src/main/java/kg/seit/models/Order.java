package kg.seit.models;

import jakarta.persistence.*;
import kg.seit.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

/**
 * @author seiitbeknarynbaev
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {MERGE,PERSIST})
    private Customer customer;

    @OneToOne(cascade = {PERSIST,MERGE},orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "point_a")
    private Address pointA;

    @OneToOne(cascade = {PERSIST,MERGE},orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "point_b")
    private Address pointB;

    @Column(name = "date_time")
    private LocalDateTime date;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {PERSIST,MERGE})
    @ToString.Exclude
    private Supplier supplier;

    @Column(name = "status")
    private OrderStatus status;



    public Order() {
    }

    public Order(LocalDateTime date, BigDecimal price, OrderStatus status) {
        this.date = date;
        this.price = price;
        this.status = status;
    }

    public Order(Customer customer, Address pointA, Address pointB, LocalDateTime date, BigDecimal price, Supplier supplier, OrderStatus status) {
        this.customer = customer;
        this.pointA = pointA;
        this.pointB = pointB;
        this.date = date;
        this.price = price;
        this.supplier = supplier;
        this.status = status;
    }
}

