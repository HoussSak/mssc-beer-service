package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
    private final BeerService beerService;
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity<>(beerService.getById(beerId),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<BeerDto> saveNewBeer (@Valid  @RequestBody BeerDto beerDto) {
       // HttpHeaders headers= new HttpHeaders();
       // headers.add("Location","hhhh");
        return new ResponseEntity<>(beerService.saveNewBeer(beerDto),HttpStatus.CREATED) ;
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateNewBeer (@Valid @RequestBody BeerDto beerDto,@PathVariable UUID beerId) {
        return new ResponseEntity<>(beerService.updateBeer(beerId,beerDto),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public void deleteBeer (@PathVariable UUID beerId) {
        beerService.deleteBeerById(beerId);
    }
}
