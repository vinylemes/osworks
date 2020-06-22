package br.com.vlemes.osworks.api.controller;

import br.com.vlemes.osworks.api.model.CostumerInput;
import br.com.vlemes.osworks.api.model.CostumerRepresentationModel;
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
    public List<CostumerRepresentationModel> getAll() {
        return costumerService.listAll();
    }

    @GetMapping("/{costumerId}")
    public ResponseEntity<CostumerRepresentationModel> getById(@PathVariable Long costumerId) {
        return costumerService.listById(costumerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CostumerRepresentationModel insert(@Valid @RequestBody CostumerInput costumerInput) {
        return costumerService.create(costumerInput);
    }

    @PutMapping("/{costumerId}")
    public ResponseEntity<CostumerRepresentationModel> update(@Valid @PathVariable Long costumerId,
                                                              @RequestBody CostumerInput costumerInput) {
        return costumerService.update(costumerId, costumerInput);
    }

    @DeleteMapping("/{costumerId}")
    public ResponseEntity<Void> delete(@PathVariable Long costumerId) {
        return costumerService.delete(costumerId);
    }
}
