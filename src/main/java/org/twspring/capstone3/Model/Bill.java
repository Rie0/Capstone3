package org.twspring.capstone3.Model;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Products amount must be not null")
    @Column(columnDefinition = "DOUBLE NOT NULL")
    @PositiveOrZero(message = "Products amount cannot be a negative or a zero")
    private double productsAmount;

    @NotNull(message = "Shipping fee must be not null")
    @Column(columnDefinition = "DOUBLE NOT NULL")
    @PositiveOrZero(message = "Shipping Fee cannot be a negative or a zero")
    private double shippingFee;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime issuedAt;

    // RELATIONSHIPS
    @ManyToOne
    @JsonIgnore
    private ArtEnthusiast artEnthusiast;

    @OneToOne
    @MapsId
    @JsonIgnore
    private ArtOrder artOrder;
}
