package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(columnDefinition = "VARCHAR(35) NOT NULL UNIQUE")
    @NotEmpty(message = "title cannot be empty")
    @Size(min=4,max = 25, message = "title must have between 4 to 25 characters")
    private String title;

    @Column(columnDefinition = "VARCHAR(500) NOT NULL")
    @NotEmpty(message = "description cannot be empty")
    @Size(min=20,max = 500, message = "description must have between 20 to 500 characters")
    private String description;


    @Column(columnDefinition = "VARCHAR(120) NOT NULL")
    @NotEmpty(message = "location cannot be empty")
    @Size(min=20,max = 120, message = "location must have between 20 to 150 characters")
    private String location;


    @Column(columnDefinition = "BOOLEAN NOT NULL")
    @NotNull(message = "is available cannot be null")
    private boolean isAvailableForRent; //Artist Can rent

    @Column(columnDefinition = "BOOLEAN NOT NULL")
    @NotNull(message = "is available cannot be null")
    private boolean isOpen; //AE can buy tickets //(change the name to can buy ticket)

    @Column(columnDefinition = "INT NOT NULL")
    @NotNull(message = "capacity cannot be null")
    @Positive(message = "capacity cannot be a zero or a negative number")
    private Integer maxCapacity;

    @Column(columnDefinition = "INT NOT NULL")
    @NotNull(message = "capacity cannot be null")
    @PositiveOrZero(message = "capacity cannot be a negative number")
    private Integer currentCapacity = 0;

    @Column(columnDefinition = "DOUBLE NOT NULL")
    @NotNull(message = "capacity cannot be null")
    @PositiveOrZero(message = "capacity cannot be a negative number")
    private double ticketPrice = 0; //change when renting, reset after

    @Column(columnDefinition = "DOUBLE NOT NULL")
    @NotNull(message = "Service fee cannot be null")
    @PositiveOrZero(message = "Service fee cannot be a negative number")
    private double serviceFee; //Fee is added to ticket amount when Art Enthusiasts buy them

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE")
    private LocalDate endDate;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private LocalDateTime updatedAt;

    //relations
    @ManyToOne
    @JsonIgnore
    private Organizer organizer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibition")
    private Set<ExhibitionTicket> exhibitionTickets;

    @ManyToOne
    @JsonIgnore
    private Artist artist;

}
