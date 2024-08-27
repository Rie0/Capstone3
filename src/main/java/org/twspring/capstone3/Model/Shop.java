package org.twspring.capstone3.Model;


import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @Positive(message = "Commission price cannot be a negative number or a zero")
    @NotNull(message = "Minimal Price must be not null")
    @Column(columnDefinition = "double not null")
    private double minimalCommissionPrice;


    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;


    //relationships

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    private Set<Product> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    private Set<CommissionRequest> commissionRequests;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Artist artist;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private Set<ArtPieceForSale> artPieceForSales;
}
