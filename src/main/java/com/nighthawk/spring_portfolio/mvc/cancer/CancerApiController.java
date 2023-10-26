package src.main.java.com.nighthawk.spring_portfolio.mvc.cancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*") // added this line
@RestController
@RequestMapping("/api/cancers")
public class CancerApiController {

    @Autowired
    private CancerJpaRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Cancer>> getCancers() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/byDeathRate/{rate}")
    public ResponseEntity<List<Cancer>> getCancersByDeathRate(@PathVariable double rate) {
        List<Cancer> cancers = repository.findByDeathRate(rate);
        if (cancers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cancers, HttpStatus.OK);
    }

    @GetMapping("/byCancerType/{type}")
    public ResponseEntity<List<Cancer>> getCancersByCancerType(@PathVariable String type) {
    List<Cancer> cancers = repository.findByCancerTypeIgnoreCase(type);
    if (cancers.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(cancers, HttpStatus.OK);
    }


    @GetMapping("/byNumOfPeopleAffected/{num}")
    public ResponseEntity<List<Cancer>> getCancersByNumOfPeopleAffected(@PathVariable int num) {
        List<Cancer> cancers = repository.findByNumOfPeopleAffected(num);
        if (cancers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cancers, HttpStatus.OK);
    }

    @GetMapping("/byAverageRecoveryTime/{time}")
    public ResponseEntity<List<Cancer>> getCancersByAverageRecoveryTime(@PathVariable int time) {
        List<Cancer> cancers = repository.findByAverageRecoveryTime(time);
        if (cancers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cancers, HttpStatus.OK);
    }

    @GetMapping("/bySymptoms/{symptoms}")
    public ResponseEntity<List<Cancer>> getCancersBySymptoms(@PathVariable String symptoms) {
        List<Cancer> cancers = repository.findBySymptoms(symptoms);
        if (cancers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cancers, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Cancer> addCancer(@RequestBody Cancer cancer) {
        repository.save(cancer);
        return new ResponseEntity<>(cancer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cancer> updateCancer(@PathVariable long id, @RequestBody Cancer updatedCancer) {
        Optional<Cancer> optional = repository.findById(id);
        if (optional.isPresent()) {
            Cancer existingCancer = optional.get();
            existingCancer.setCancerType(updatedCancer.getCancerType());
            existingCancer.setNumOfPeopleAffected(updatedCancer.getNumOfPeopleAffected());
            existingCancer.setDeathRate(updatedCancer.getDeathRate());
            existingCancer.setAverageRecoveryTime(updatedCancer.getAverageRecoveryTime());
            existingCancer.setSymptoms(updatedCancer.getSymptoms());

            repository.save(existingCancer);
            return new ResponseEntity<>(existingCancer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCancer(@PathVariable long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}