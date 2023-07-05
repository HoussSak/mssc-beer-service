package guru.springframework.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.junit.jupiter.api.Assertions.*;
@JsonTest
class BeerJsonTest extends BaseTest{
    @Autowired
    ObjectMapper objectMapper;
    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerJson beerJson = getDto();
        String jsonString = objectMapper.writeValueAsString(beerJson);
        System.out.println(jsonString);

    }
    @Test
    void testDeserialize() throws JsonProcessingException {
        String json = "{\"id\":\"8e4521e8-f10b-4ba0-801c-a798945f46f1\",\"beerName\":\"BeerName\",\"beerStyle\":\"Ale\",\"upc\":123123123123,\"createdDate\":\"2023-07-05T00:00:00Z\",\"lastUpdatedDate\":\"2023-07-05T15:28:06.657686+02:00\",\"myLocalDate\":\"20230705\",\"lol\":\"12.99\"}\n";
        BeerJson beerJson = objectMapper.readValue(json, BeerJson.class);
        System.out.println(beerJson);
    }

}