package com.anderson.app.geoapp.controllers.response;

import lombok.*;

import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeaturePageResponse {
    List<FeatureResponse> data;
    private Map<String, Object> pagination;
}
