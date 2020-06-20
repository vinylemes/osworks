package br.com.vlemes.osworks.domain.service;

import br.com.vlemes.osworks.domain.model.ServiceOrder;
import br.com.vlemes.osworks.domain.model.ServiceOrderStatus;
import br.com.vlemes.osworks.domain.repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    public ServiceOrder create(ServiceOrder serviceOrder) {
        serviceOrder.setStatus(ServiceOrderStatus.ABERTA);
        serviceOrder.setOpeningDate(LocalDateTime.now());
        return serviceOrderRepository.save(serviceOrder);
    }
}
