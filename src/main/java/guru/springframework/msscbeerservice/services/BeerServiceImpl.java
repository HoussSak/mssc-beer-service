package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.NotFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
@EnableCaching
@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    public BeerServiceImpl(BeerRepository beerRepository, BeerMapper beerMapper) {
        this.beerRepository = beerRepository;
        this.beerMapper = beerMapper;
    }
    @Cacheable(value = "beerCache",key ="#beerId" ,condition = "!#showInventoryOnHand")
    @Override
    public BeerDto getById(UUID beerId,Boolean showInventoryOnHand) {
        System.out.println("i was called");
        if (showInventoryOnHand) {
           return beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        beerRepository.deleteById(beerId);
    }
    @Cacheable(value = "beerListCache" ,condition = "!#showInventoryOnHand")
    @Override
    public BeerPagedList getBeerList(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        System.out.println("i was called");
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;
        if(!Objects.isNull(beerName) && !Objects.isNull(beerStyle)) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName,beerStyle,pageRequest);
        } else if (!Objects.isNull(beerName)) {
            beerPage = beerRepository.findAllByBeerName(beerName,pageRequest);
        } else if(!Objects.isNull(beerStyle)) {
            beerPage = beerRepository.findAllByBeerStyle(beerStyle,pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }
        if (showInventoryOnHand) {
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest.of(
                            beerPage.getPageable().getPageNumber(),
                            beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
            return beerPagedList;

        }

        beerPagedList = new BeerPagedList(beerPage
                .getContent()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList()),
                PageRequest.of(
                        beerPage.getPageable().getPageNumber(),
                        beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());
        return beerPagedList;
    }
    @Cacheable(value = "beerUpcCache" ,condition = "!#showInventoryOnHand")
    @Override
    public BeerDto getByUpc(String upc, Boolean showInventoryOnHand) {
        System.out.println("i was called");
        if (showInventoryOnHand) {
            return beerMapper.beerToBeerDtoWithInventory(beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new));
        }
        return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new));
    }
}
