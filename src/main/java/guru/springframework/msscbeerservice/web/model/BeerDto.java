package guru.springframework.msscbeerservice.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
@Builder
public record BeerDto (@Null UUID id,
        @Null
        Integer version,
        @Null
        OffsetDateTime createdDate,
        @Null
        OffsetDateTime lastModifiedDate,
        @NotBlank
        String beerName,
        @NotNull
        BeerStyleEnum beerStyle,
        @NotNull
        @Positive
        Long upc,
        @Positive
        @NotNull
        BigDecimal price,
        @Positive
        Integer quantityOnHand
) {

}
