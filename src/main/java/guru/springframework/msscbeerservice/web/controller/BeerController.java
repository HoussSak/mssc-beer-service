package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class BeerController {
    private final BeerService beerService;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/beer")
    public ResponseEntity<BeerPagedList> listBeers(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "beerName", required = false) String beerName,
            @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
            @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand

    ) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

         BeerPagedList beerList =  beerService.getBeerList(beerName,beerStyle, PageRequest.of(pageNumber,pageSize),showInventoryOnHand);

        return new ResponseEntity<>(beerList,HttpStatus.OK);
    }
    @GetMapping("/beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId,
                                               @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand
    ) {
        return new ResponseEntity<>(beerService.getById(beerId,showInventoryOnHand),HttpStatus.OK);
    }
    @GetMapping("/beerupc/{upc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable String upc,
                                               @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand
    ) {
        return new ResponseEntity<>(beerService.getByUpc(upc,showInventoryOnHand),HttpStatus.OK);
    }
    @PostMapping("/beer")
    public ResponseEntity<BeerDto> saveNewBeer (@Valid  @RequestBody BeerDto beerDto) {
       // HttpHeaders headers= new HttpHeaders();
       // headers.add("Location","hhhh");
        return new ResponseEntity<>(beerService.saveNewBeer(beerDto),HttpStatus.CREATED) ;
    }

    @PutMapping("/beer/{beerId}")
    public ResponseEntity<BeerDto> updateNewBeer (@Valid @RequestBody BeerDto beerDto,@PathVariable UUID beerId) {
        return new ResponseEntity<>(beerService.updateBeer(beerId,beerDto),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/beer/{beerId}")
    public void deleteBeer (@PathVariable UUID beerId) {
        beerService.deleteBeerById(beerId);
    }
}
