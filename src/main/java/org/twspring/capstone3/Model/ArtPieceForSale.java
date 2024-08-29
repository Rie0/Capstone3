package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity

public class ArtPieceForSale extends Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name should not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    @Size(min=4,max = 50, message = "Name must have between 4 to 50 characters")
    private String name;


    @NotEmpty(message = "About should not be empty")
    @Column(columnDefinition = "varchar(500) not null")
    @Size(min=4,max = 500, message = "About must have between 4 to 500 characters")
    private String about;


    @NotNull(message = "Price should not be null")
    @Column(columnDefinition = "DOUBLE not null")
    private Double price;


    @Column(columnDefinition = "Boolean default false")
    private boolean isSold = false;

    @ManyToOne
    @JsonIgnore
    private ArtOrder artOrder;
    
}
