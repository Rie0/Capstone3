package org.twspring.capstone3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 4, max = 500)
    private String bio;

    @CreationTimestamp
    private LocalDateTime updatedAt;
}
