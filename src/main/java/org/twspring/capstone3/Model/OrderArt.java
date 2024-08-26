package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class OrderArt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp not null default current_timestamp")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private LocalDateTime updatedAt;

    // ArtEnthusiast_Id
    @ManyToOne
    @JsonIgnore
    private ArtEnthusiast artEnthusiast;

    // artist_id
    @ManyToOne
    @JsonIgnore
    private Artist artist;

    // product_id
    @ManyToOne
    @JsonIgnore
    private Product product;
}
