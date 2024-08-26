package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "Created date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT TIMESTAMP(CURRENT_DATE)")
    private LocalDate createdAt = LocalDate.now();

    @NotNull(message = "Updated date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT TIMESTAMP(CURRENT_DATE)")
    private LocalDate updatedAt = LocalDate.now();



}
