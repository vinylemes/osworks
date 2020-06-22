package br.com.vlemes.osworks.api.controller;

import br.com.vlemes.osworks.api.model.ServiceOrderInput;
import br.com.vlemes.osworks.api.model.ServiceOrderRepresentationModel;
import br.com.vlemes.osworks.domain.service.ServiceOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/service-orders")
@Api(value = "Service Order")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @ApiOperation(value = "Cadastro de Ordem de Serviço")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrderRepresentationModel create(@Valid @RequestBody ServiceOrderInput serviceOrderInput) {
        return serviceOrderService.create(serviceOrderInput);
    }

    @ApiOperation(value = "Listar todas as Ordens de Serviço")
    @GetMapping
    public List<ServiceOrderRepresentationModel> getAll() {
        return serviceOrderService.listAll();
    }

    @ApiOperation(value = "Listar ordem de serviço por ID")
    @GetMapping("/{serviceOrderId}")
    public ResponseEntity<ServiceOrderRepresentationModel> getById(@PathVariable Long serviceOrderId) {
        return serviceOrderService.listById(serviceOrderId);
    }

    @ApiOperation(value = "Fechar ordem de serviço")
    @PutMapping("/{serviceOrderId}/closure")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long serviceOrderId) {
        serviceOrderService.close(serviceOrderId);
    }

    @ApiOperation(value = "Cancelar ordem de serviço")
    @PutMapping("/{serviceOrderId}/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellation(@PathVariable Long serviceOrderId) {
        serviceOrderService.cancellation(serviceOrderId);
    }

}
