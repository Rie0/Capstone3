package org.twspring.capstone3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ArtistProfileDTO {
    @NotNull(message = "artist id cannot be null")
    private Integer artist_id;

    @NotEmpty(message = "Bio cannot be empty")
    private String bio;

    @CreationTimestamp
    @NotNull(message = "Updated date cannot be null")
    private LocalDateTime updatedAt;
}
