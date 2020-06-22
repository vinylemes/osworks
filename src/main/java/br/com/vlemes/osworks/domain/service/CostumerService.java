package br.com.vlemes.osworks.domain.service;

import br.com.vlemes.osworks.api.model.CostumerInput;
import br.com.vlemes.osworks.api.model.CostumerRepresentationModel;
import br.com.vlemes.osworks.domain.exception.DomainException;
import br.com.vlemes.osworks.domain.model.Costumer;
import br.com.vlemes.osworks.domain.repository.CostumerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CostumerService {

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CostumerRepresentationModel> listAll() {
        return toCollectionModel(costumerRepository.findAll());
    }

    public ResponseEntity<CostumerRepresentationModel> listById(Long costumerId) {
        Optional<Costumer> costumer = costumerRepository.findById(costumerId);
        if (costumer.isPresent()) {
            CostumerRepresentationModel costumerRepresentationModel = toModel(costumer.get());
            return ResponseEntity.ok(costumerRepresentationModel);
        }
        return ResponseEntity.notFound().build();
    }

    public CostumerRepresentationModel create(CostumerInput costumerInput) {
        Costumer costumer = toEntity(costumerInput);
        Costumer existingCostumer = costumerRepository.findByEmail(costumer.getEmail());
        if (existingCostumer != null && !existingCostumer.equals(costumer)) {
            throw new DomainException("Já existe um cliente cadastrado com este e-mail.");
        }
        return toModel(costumerRepository.save(costumer));
    }

    public ResponseEntity<CostumerRepresentationModel> update(Long costumerId, CostumerInput costumerInput) {
        Costumer costumer = toEntity(costumerInput);
        if (!costumerRepository.existsById(costumerId)) {
            return ResponseEntity.notFound().build();
        }
        costumer.setId(costumerId);
        costumer = costumerRepository.save(costumer);
        return ResponseEntity.ok(toModel(costumer));
    }

    public ResponseEntity<Void> delete(Long costumerId) {
        if (!costumerRepository.existsById(costumerId)) {
            return ResponseEntity.notFound().build();
        }
        costumerRepository.deleteById(costumerId);
        return ResponseEntity.noContent().build();
    }

    private CostumerRepresentationModel toModel(Costumer costumer) {
        return modelMapper.map(costumer, CostumerRepresentationModel.class);
    }

    private List<CostumerRepresentationModel> toCollectionModel(List<Costumer> costumers) {
        return costumers.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private Costumer toEntity(CostumerInput costumerInput) {
        return modelMapper.map(costumerInput, Costumer.class);
    }
}
