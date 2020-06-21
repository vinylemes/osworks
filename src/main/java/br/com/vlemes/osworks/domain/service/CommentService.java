package br.com.vlemes.osworks.domain.service;

import br.com.vlemes.osworks.api.model.CommentRepresentationModel;
import br.com.vlemes.osworks.domain.exception.DomainException;
import br.com.vlemes.osworks.domain.exception.EntityNotFoundException;
import br.com.vlemes.osworks.domain.model.Comment;
import br.com.vlemes.osworks.domain.model.ServiceOrder;
import br.com.vlemes.osworks.domain.repository.CommentRepository;
import br.com.vlemes.osworks.domain.repository.ServiceOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CommentRepresentationModel> listAll(Long serviceOrdemId) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrdemId)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada"));
        return toCollectionModel(serviceOrder.getComments());
    }

    public CommentRepresentationModel create(long serviceOrderId, String description) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada"));

        Comment comment = new Comment();
        comment.setServiceOrder(serviceOrder);
        comment.setDescription(description);
        comment.setSendDate(OffsetDateTime.now());

        return toModel(commentRepository.save(comment));
    }

    private CommentRepresentationModel toModel(Comment comment) {
        return modelMapper.map(comment, CommentRepresentationModel.class);
    }

    private List<CommentRepresentationModel> toCollectionModel(List<Comment> comments) {
        return comments.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
