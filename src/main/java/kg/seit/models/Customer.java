package kg.seit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

/**
 * @author seiitbeknarynbaev
 */
@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private long phoneNumber;

    @OneToOne(cascade = {PERSIST,MERGE},orphanRemoval = true,fetch = FetchType.EAGER)
    private Address address;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER,cascade = {PERSIST, MERGE},orphanRemoval = true)
    @Column(name = "orders")
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String fullName, long phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String fullName, long phoneNumber, Address address, List<Order> orders) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
