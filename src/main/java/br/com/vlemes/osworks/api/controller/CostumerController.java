package br.com.vlemes.osworks.api.controller;

import br.com.vlemes.osworks.domain.model.Costumer;
import br.com.vlemes.osworks.domain.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/costumers")
public class CostumerController {

    @Autowired
    private CostumerRepository costumerRepository;

    @GetMapping
    public List<Costumer> getAll() {
        return costumerRepository.findAll();
    }

    @GetMapping("/{costumerId}")
    public ResponseEntity<Costumer> getById(@PathVariable Long costumerId) {
        Optional<Costumer> costumer = costumerRepository.findById(costumerId);
        return costumer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Costumer insert(@Valid @RequestBody Costumer costumer) {
        return costumerRepository.save(costumer);
    }

    @PutMapping("/{costumerId}")
    public ResponseEntity<Costumer> update(@Valid @PathVariable Long costumerId, @RequestBody Costumer costumer) {
        if (!costumerRepository.existsById(costumerId)) {
            return ResponseEntity.notFound().build();
        }
        costumer.setId(costumerId);
        costumer = costumerRepository.save(costumer);
        return ResponseEntity.ok(costumer);
    }

    @DeleteMapping("/{costumerId}")
    public ResponseEntity<Void> delete(@PathVariable Long costumerId) {
        if (!costumerRepository.existsById(costumerId)) {
            return ResponseEntity.notFound().build();
        }
        costumerRepository.deleteById(costumerId);
        return ResponseEntity.noContent().build();
    }
}
