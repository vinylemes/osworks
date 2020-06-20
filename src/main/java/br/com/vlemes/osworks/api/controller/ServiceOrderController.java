package br.com.vlemes.osworks.api.controller;

import br.com.vlemes.osworks.domain.model.ServiceOrder;
import br.com.vlemes.osworks.domain.service.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrder create(@RequestBody ServiceOrder serviceOrder) {
        return serviceOrderService.create(serviceOrder);
    }
}
