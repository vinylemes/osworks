package br.com.vlemes.osworks.api.controller;

import br.com.vlemes.osworks.domain.model.Costumer;
import br.com.vlemes.osworks.domain.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/costumers")
public class CostumerController {

    @Autowired
    private CostumerService costumerService;

    @GetMapping
    public List<Costumer> getAll() {
        return costumerService.listAll();
    }

    @GetMapping("/{costumerId}")
    public ResponseEntity<Costumer> getById(@PathVariable Long costumerId) {
        return costumerService.listById(costumerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Costumer insert(@Valid @RequestBody Costumer costumer) {
        return costumerService.save(costumer);
    }

    @PutMapping("/{costumerId}")
    public ResponseEntity<Costumer> update(@Valid @PathVariable Long costumerId, @RequestBody Costumer costumer) {
        return costumerService.update(costumerId, costumer);
    }

    @DeleteMapping("/{costumerId}")
    public ResponseEntity<Void> delete(@PathVariable Long costumerId) {
        return costumerService.delete(costumerId);
    }
}
