package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.security.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ArtistProfile {
    @Id
    private Integer id;
    @NotEmpty(message = "bio cannot be empty")
    @Column(columnDefinition = "varchar(500) not null")
    private String bio;
    @NotNull(message = "Updated date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT TIMESTAMP(CURRENT_DATE)")
    private LocalDate updatedAt = LocalDate.now();
    @OneToOne
    @MapsId
    @JsonIgnore
    private Artist artist;

}
