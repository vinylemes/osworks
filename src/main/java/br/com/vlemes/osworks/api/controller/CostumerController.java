package br.com.vlemes.osworks.api.controller;

import br.com.vlemes.osworks.domain.model.Costumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CostumerController {

    @GetMapping("/costumers")
    public List<Costumer> listAll(){
        var costumer1 = new Costumer();
        costumer1.setId(1L);
        costumer1.setName("Vinicius Lemes");
        costumer1.setPhoneNumber("16993791052");
        costumer1.setEmail("lemesalves97@gmail.com");

        var costumer2 = new Costumer();
        costumer2.setId(2L);
        costumer2.setName("Lais Riquieri");
        costumer2.setPhoneNumber("83991414631");
        costumer2.setEmail("lemesalves@hotmail.com");

        return Arrays.asList(costumer1, costumer2);

    }
}
