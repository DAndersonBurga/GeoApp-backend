package com.anderson.app.geoapp.service;

import com.anderson.app.geoapp.controllers.request.CreateCommentRequest;
import com.anderson.app.geoapp.controllers.response.CommentCreatedResponse;
import com.anderson.app.geoapp.entity.Comment;

import java.util.List;

public interface CommentService {
    CommentCreatedResponse createComment (CreateCommentRequest commentRequest);
    List<Comment> findAllCommentsByFeatureId(Long id);
}
