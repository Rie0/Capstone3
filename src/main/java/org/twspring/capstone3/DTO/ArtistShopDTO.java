package org.twspring.capstone3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArtistShopDTO {
    private Integer artist_id;

//    @NotNull
    private boolean isCommissionOpen;

    @Positive(message = "Commission price cannot be a negative number or a zero")
    @NotNull(message = "Minimal Price must be not null")
    private double minimalCommissionPrice;
}
