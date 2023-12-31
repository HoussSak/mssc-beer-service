package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.bootstrap.BeerLoader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.*;
@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateTest {
    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getOnHandInventory() {
        Integer onHandInventory = beerInventoryService.getOnHandInventory(BeerLoader.BEER_1_UUID);
        System.out.println(onHandInventory);
    }
}