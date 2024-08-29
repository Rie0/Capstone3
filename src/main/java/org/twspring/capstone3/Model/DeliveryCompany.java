package org.twspring.capstone3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class DeliveryCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(25) not null unique")
    @NotEmpty(message = "Name should not be empty")
    @Size(min=4,max = 25, message = "Name must have between 4 to 25 characters")
    private String name;

    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "Time range cannot be empty")
    private String timeRange;

    @Column(columnDefinition = "double not null")
    @NotNull(message = "Delivery fee cannot be null")
    @Positive(message = "Delivery fee cannot be a zero or a negative number")
    private double deliveryFee;

    @OneToMany(cascade =  CascadeType.ALL, mappedBy ="deliveryCompany")
    private Set<ArtOrder> artOrders;
}