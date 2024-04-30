package com.anderson.app.geoapp.controllers.response;

import com.anderson.app.geoapp.entity.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeatureResponse {
    private Long id;
    private String type = "feature";
    Map<String, Object> attributes;
    Map<String, Object> links;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<Comment> comments;
}
