package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class ExhibitionTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "artEnthusiast_id", referencedColumnName = "id")
    @JsonIgnore
    private ArtEnthusiast artEnthusiast;


    @ManyToOne
    @JoinColumn(name = "exhibition_id", referencedColumnName = "id")
    @JsonIgnore
    private Exhibition exhibition;

    @NotNull(message = "Amount cannot be empty")
    @Column(columnDefinition = "DOUBLE not null")
    @PositiveOrZero(message = "Amount cannot be a negative number")
    private Double amount;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime purchaseDate;


    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp not null default current_timestamp on update current_timestamp")
    private LocalDateTime updatedAt;

}
