package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class ArtWork {
    @Id
    private  Integer id;
    @NotEmpty(message = "description cannot be empty")
    @Column(columnDefinition = "varchar(200) not null")
    private  String description;
    @NotEmpty(message = "image url cannot be empty")
    @Column(columnDefinition = "varchar(200) not null")
    private  String imageUrl;
    @NotNull(message = "Created date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT TIMESTAMP(CURRENT_DATE)")
    private LocalDate publishedAt = LocalDate.now();

    @NotNull(message = "Updated date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT TIMESTAMP(CURRENT_DATE)")
    private LocalDate updatedAt = LocalDate.now();











}
