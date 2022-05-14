package kg.seit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author seiitbeknarynbaev
 */
@Entity
@Table(name = "addresses")
@Getter
@Setter
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "street")
    private String street;

    public Address() {
    }

    public Address(String country, String city, String region, String street) {
        this.country = country;
        this.city = city;
        this.region = region;
        this.street = street;
    }
}
