package guru.springframework.msscbeerservice.web.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
@Builder
public record BeerDto (UUID id,
        Integer version,
        OffsetDateTime createdDate,
        OffsetDateTime lastModifiedDate,
        String beerName,
        BeerStyleEnum beerStyle,
        Long upc,
        BigDecimal price,
        Integer quantityOnHand
) {

}
