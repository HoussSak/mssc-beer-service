package guru.springframework.msscbeerservice.repositories;

import guru.springframework.msscbeerservice.domain.Beer;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface BeerRepository extends ListCrudRepository<Beer, UUID> {
}
