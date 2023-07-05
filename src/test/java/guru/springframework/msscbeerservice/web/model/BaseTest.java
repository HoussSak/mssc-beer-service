package guru.springframework.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTest {
    BeerJson getDto(){
        return  BeerJson.builder()
                .beerName("BeerName")
                .beerStyle("Ale")
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastUpdatedDate(OffsetDateTime.now())
                .price(new BigDecimal("12.99"))
                .upc(123123123123L)
                .myLocalDate(LocalDate.now())
                .build();
    }
}
