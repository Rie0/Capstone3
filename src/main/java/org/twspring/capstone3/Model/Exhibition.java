package org.twspring.capstone3.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer currentArtistId;

    private String title;
    private String description;
    private String location;
    private double price;
    private boolean isAvailable;
    private Integer capacity;

    private LocalDate date;



    private LocalDate createdDate;
    private LocalDate updatedDate;
}
