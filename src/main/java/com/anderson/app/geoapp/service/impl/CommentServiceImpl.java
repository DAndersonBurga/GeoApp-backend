package com.anderson.app.geoapp.service.impl;

import com.anderson.app.geoapp.controllers.request.CreateCommentRequest;
import com.anderson.app.geoapp.controllers.response.CommentCreatedResponse;
import com.anderson.app.geoapp.entity.Comment;
import com.anderson.app.geoapp.entity.Feature;
import com.anderson.app.geoapp.repository.CommentRepository;
import com.anderson.app.geoapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentCreatedResponse createComment(CreateCommentRequest commentRequest) {

        Comment comment = Comment.builder()
                .body(commentRequest.getBody())
                .feature(Feature.builder()
                        .id(commentRequest.getFeatureId())
                        .build())
                .build();

        commentRepository.save(comment);

        return new CommentCreatedResponse("Comentario creado");
    }

    @Override
    public List<Comment> findAllCommentsByFeatureId(Long id) {
        return commentRepository.findAllByFeatureId(id);
    }
}
