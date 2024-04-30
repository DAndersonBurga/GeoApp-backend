package com.anderson.app.geoapp.controllers.api;

import com.anderson.app.geoapp.controllers.request.CreateCommentRequest;
import com.anderson.app.geoapp.controllers.response.CommentCreatedResponse;
import com.anderson.app.geoapp.controllers.response.FeaturePageResponse;
import com.anderson.app.geoapp.controllers.response.FeatureResponse;
import com.anderson.app.geoapp.entity.Comment;
import com.anderson.app.geoapp.service.CommentService;
import com.anderson.app.geoapp.service.FeatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/features")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WebController {

    private final FeatureService featureService;
    private final CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<FeaturePageResponse> allFeatures(Pageable pageable,
                                                     @RequestParam(required = false, name = "mag_type") String magType) {
        FeaturePageResponse featurePageResponse = featureService.findAll(pageable, magType);
        
        return ResponseEntity.ok(featurePageResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeatureResponse> findFeaturesAndComments(@PathVariable Long id) {
        FeatureResponse featureResponse = featureService.findById(id);
        List<Comment> comments = commentService.findAllCommentsByFeatureId(id);

        featureResponse.setComments(comments);

        return ResponseEntity.ok(featureResponse);

    }

    @PostMapping("/comment/create")
    public ResponseEntity<CommentCreatedResponse> createCommentInFeature(@RequestBody @Valid CreateCommentRequest createCommentRequest) {
        CommentCreatedResponse response = commentService.createComment(createCommentRequest);

        return ResponseEntity.ok(response);
    }
}
