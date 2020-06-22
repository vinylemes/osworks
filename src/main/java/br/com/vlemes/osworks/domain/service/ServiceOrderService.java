package br.com.vlemes.osworks.domain.service;

import br.com.vlemes.osworks.api.model.ServiceOrderInput;
import br.com.vlemes.osworks.api.model.ServiceOrderRepresentationModel;
import br.com.vlemes.osworks.domain.exception.DomainException;
import br.com.vlemes.osworks.domain.exception.EntityNotFoundException;
import br.com.vlemes.osworks.domain.model.Costumer;
import br.com.vlemes.osworks.domain.model.ServiceOrder;
import br.com.vlemes.osworks.domain.model.ServiceOrderStatus;
import br.com.vlemes.osworks.domain.repository.CostumerRepository;
import br.com.vlemes.osworks.domain.repository.ServiceOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ServiceOrderRepresentationModel> listAll() {
        return toCollectionModel(serviceOrderRepository.findAll());
    }

    public ResponseEntity<ServiceOrderRepresentationModel> listById(Long serviceOrderId) {
        Optional<ServiceOrder> serviceOrder = serviceOrderRepository.findById(serviceOrderId);
        if (serviceOrder.isPresent()) {
            ServiceOrderRepresentationModel serviceOrderRepresentationModel = toModel(serviceOrder.get());
            return ResponseEntity.ok(serviceOrderRepresentationModel);
        }
        return ResponseEntity.notFound().build();
    }

    public ServiceOrderRepresentationModel create(ServiceOrderInput serviceOrderInput) {
        ServiceOrder serviceOrder = toEntity(serviceOrderInput);
        Costumer costumer = costumerRepository.findById(serviceOrder.getCostumer().getId())
                .orElseThrow(() -> new DomainException("Cliente não encontrado"));

        serviceOrder.setCostumer(costumer);
        serviceOrder.setStatus(ServiceOrderStatus.ABERTA);
        serviceOrder.setOpeningDate(OffsetDateTime.now());
        return toModel(serviceOrderRepository.save(serviceOrder));

    }

    public void close(Long serviceOrderId) {
        ServiceOrder serviceOrder = findServiceOrder(serviceOrderId);
        serviceOrder.close();
        serviceOrderRepository.save(serviceOrder);
    }

    public void cancellation(Long serviceOrderId) {
        ServiceOrder serviceOrder = findServiceOrder(serviceOrderId);
        serviceOrder.cancellation();
        serviceOrderRepository.save(serviceOrder);
    }

    private ServiceOrder findServiceOrder(Long serviceOrderId) {
        return serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de servio não encontrada"));
    }

    private ServiceOrderRepresentationModel toModel(ServiceOrder serviceOrder) {
        return modelMapper.map(serviceOrder, ServiceOrderRepresentationModel.class);
    }

    private List<ServiceOrderRepresentationModel> toCollectionModel(List<ServiceOrder> serviceOrders) {
        return serviceOrders.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private ServiceOrder toEntity(ServiceOrderInput serviceOrderInput) {
        return modelMapper.map(serviceOrderInput, ServiceOrder.class);
    }

}
