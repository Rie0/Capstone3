package org.twspring.capstone3.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ArtistDTO {

    @NotEmpty(message = "Artist name cannot be empty")
    private String artistName;
}
