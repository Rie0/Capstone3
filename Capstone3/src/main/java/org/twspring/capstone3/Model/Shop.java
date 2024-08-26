package org.twspring.capstone3.Model;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "^(true|false)$", message = "Is commission open must be either('true' or 'false')")
    @Column(columnDefinition = "boolean check(isCommissionOpen = true or isCommissionOpen = false)")
    private boolean isCommissionOpen = false;

    @NotNull(message = "Minimal Price must be not null")
    @Column(columnDefinition = "double not null")
    private double minimalCommissionPrice;


    @CreationTimestamp
    @Column(columnDefinition = "timestamp not null default current_timestamp", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
