package kg.seit.models;

import jakarta.persistence.*;
import kg.seit.enums.SupplierStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seiitbeknarynbaev
 */
@Entity
@Table(name = "suppliers")
@Getter
@Setter
@ToString
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "status")
    private SupplierStatus status;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private List<Order> orders = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(String fullName, Long phoneNumber, SupplierStatus status) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Supplier(String fullName, Long phoneNumber, SupplierStatus status, List<Order> orders) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}

