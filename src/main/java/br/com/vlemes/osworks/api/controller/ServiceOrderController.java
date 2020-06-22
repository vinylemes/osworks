package br.com.vlemes.osworks.api.controller;

import br.com.vlemes.osworks.api.model.ServiceOrderInput;
import br.com.vlemes.osworks.api.model.ServiceOrderRepresentationModel;
import br.com.vlemes.osworks.domain.service.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrderRepresentationModel create(@Valid @RequestBody ServiceOrderInput serviceOrderInput) {
        return serviceOrderService.create(serviceOrderInput);
    }

    @GetMapping
    public List<ServiceOrderRepresentationModel> getAll() {
        return serviceOrderService.listAll();
    }

    @GetMapping("/{serviceOrderId}")
    public ResponseEntity<ServiceOrderRepresentationModel> getById(@PathVariable Long serviceOrderId) {
        return serviceOrderService.listById(serviceOrderId);
    }

    @PutMapping("/{serviceOrderId}/closure")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long serviceOrderId) {
        serviceOrderService.close(serviceOrderId);
    }

    @PutMapping("/{serviceOrderId}/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellation(@PathVariable Long serviceOrderId){
        serviceOrderService.cancellation(serviceOrderId);
    }

}
