package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class CommissionRequest extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "price cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer price;

    @NotEmpty(message = "description cannot be empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String description;

    @Enumerated(EnumType.STRING)
    private CommissionRequest.Status status= CommissionRequest.Status.PENDING;

    public enum Status{
        COMPLETED, //add endpoint to edit to completed
        APPROVED, //add end point to accept
        PENDING, //default
        REJECTED,
        CANCELED//add end point to reject
    }

    @ManyToOne
    @JsonIgnore
    private ArtEnthusiast artEnthusiast;

}
