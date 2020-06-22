package br.com.vlemes.osworks.api.controller;

import br.com.vlemes.osworks.api.model.CommentInput;
import br.com.vlemes.osworks.api.model.CommentRepresentationModel;
import br.com.vlemes.osworks.domain.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/service-orders/{serviceOrderId}/comments")
@Api(value = "Comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Listar os comentários de uma ordem de serviço")
    @GetMapping
    public List<CommentRepresentationModel> getAll(@PathVariable Long serviceOrderId) {
        return commentService.listAll(serviceOrderId);
    }

    @ApiOperation("Cadastrar comentário")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentRepresentationModel create(@PathVariable Long serviceOrderId,
                                             @Valid @RequestBody CommentInput commentInput) {
        return commentService.create(serviceOrderId, commentInput.getDescription());
    }
}
