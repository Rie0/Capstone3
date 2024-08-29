package org.twspring.capstone3.Model;


import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "boolean not null")
    private boolean isCommissionOpen;

    @PositiveOrZero(message = "Commission price cannot be a negative number")
    @NotNull(message = "Minimal Price must be not null")
    @Column(columnDefinition = "double not null")
    private double minimalCommissionPrice;


    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;

    //RELATIONSHIPS

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    private Set<Product> products;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Artist artist;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    private Set<ArtOrder> artOrders;

    public Shop(Integer id, boolean isCommissionOpen, double minimalCommissionPrice, Artist artist) {
        this.id = id;
        this.isCommissionOpen = isCommissionOpen;
        this.minimalCommissionPrice = minimalCommissionPrice;
        this.artist = artist;
    }
}
