package guru.springframework.msscbeerservice.web.bootstrap;

import guru.springframework.msscbeerservice.web.domain.Beer;
import guru.springframework.msscbeerservice.web.repositories.BeerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            Beer mango = Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityOnHand(200)
                    .minOnHand(12)
                    .upc(337010000001L)
                    .price(new BigDecimal("12.95"))
                    .build();

            Beer galaxy = Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityOnHand(200)
                    .minOnHand(11)
                    .upc(337010000002L)
                    .price(new BigDecimal("11.95"))
                    .build();
            beerRepository.saveAll(List.of(mango, galaxy));
        }
        log.debug("Beer counts: {} ", beerRepository.count());
    }
}
