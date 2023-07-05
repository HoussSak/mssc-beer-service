package guru.springframework.msscbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
@Builder
public record BeerDto (@Null UUID id,
        @Null
        Integer version,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ",shape = JsonFormat.Shape.STRING )
        @Null
        OffsetDateTime createdDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ",shape = JsonFormat.Shape.STRING )
        @Null
        OffsetDateTime lastModifiedDate,
        @NotBlank
        @Size(min = 3, max = 100)
        String beerName,
        @NotNull
        BeerStyleEnum beerStyle,
        @NotNull
        @Positive
        Long upc,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        @Positive
        @NotNull
        BigDecimal price,
        @Positive
        Integer quantityOnHand
) {

}
