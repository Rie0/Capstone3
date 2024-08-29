package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ArtOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @PositiveOrZero(message = "Total price cannot be negative")
    @Column(columnDefinition = "DOUBLE NOT NULL")
    private Double totalPrice=0.0;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp not null default current_timestamp")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private LocalDateTime updatedAt;


    @Enumerated(EnumType.STRING)
    private ArtOrder.Status status= ArtOrder.Status.ACTIVE;

    public enum Status{
        ACTIVE,
        SHIPPED,
        DELIVERED
    }

    //RELATIONS

    @ManyToOne
    @JsonIgnore
    private ArtEnthusiast artEnthusiast;

    // artist_id
    @ManyToOne
    @JsonIgnore
    private Shop shop;

    // product_id
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artOrder")
    private Set<ArtPieceForSale> artPieceForSale;

    @OneToOne(mappedBy = "artOrder", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Bill bill;

    @ManyToOne
    @JsonIgnore
    private DeliveryCompany deliveryCompany;
}
