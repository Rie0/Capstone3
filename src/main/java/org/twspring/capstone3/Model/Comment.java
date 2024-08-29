package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "artwork_id", nullable = false)
    private ArtWork artWork;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "art_enthusiast_id", nullable = false)
    private ArtEnthusiast artEnthusiast;

    @Column(columnDefinition = "varchar(300)")
    @Size(min = 4, max = 300)
    @NotEmpty(message = "text cannot be empty")
    @NotBlank(message = "text cannot be blank")
    private String text;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;
}
