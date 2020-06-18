package br.com.vlemes.osworks.domain.service;

import br.com.vlemes.osworks.domain.exception.DomainException;
import br.com.vlemes.osworks.domain.model.Costumer;
import br.com.vlemes.osworks.domain.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerService {

    @Autowired
    private CostumerRepository costumerRepository;

    public List<Costumer> listAll() {
        return costumerRepository.findAll();
    }

    public ResponseEntity<Costumer> listById(Long costumerId) {
        Optional<Costumer> costumer = costumerRepository.findById(costumerId);
        return costumer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Costumer save(Costumer costumer) {
        Costumer existingCostumer = costumerRepository.findByEmail(costumer.getEmail());

        if (existingCostumer != null && !existingCostumer.equals(costumer)) {
            throw new DomainException("Já existe um cliente cadastrado com este e-mail.");
        }
        return costumerRepository.save(costumer);
    }

    public ResponseEntity<Costumer> update(Long costumerId, Costumer costumer) {
        if (!costumerRepository.existsById(costumerId)) {
            return ResponseEntity.notFound().build();
        }
        costumer.setId(costumerId);
        costumer = costumerRepository.save(costumer);
        return ResponseEntity.ok(costumer);
    }

    public ResponseEntity<Void> delete(Long costumerId) {
        if (!costumerRepository.existsById(costumerId)) {
            return ResponseEntity.notFound().build();
        }
        costumerRepository.deleteById(costumerId);
        return ResponseEntity.noContent().build();
    }
}
