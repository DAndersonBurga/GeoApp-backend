package com.anderson.app.geoapp.repository;

import com.anderson.app.geoapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByFeatureId(Long featureId);
}
