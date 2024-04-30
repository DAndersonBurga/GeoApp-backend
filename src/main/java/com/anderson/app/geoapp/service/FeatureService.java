package com.anderson.app.geoapp.service;

import com.anderson.app.geoapp.controllers.response.FeaturePageResponse;
import com.anderson.app.geoapp.controllers.response.FeatureResponse;
import org.springframework.data.domain.Pageable;

public interface FeatureService {
    FeaturePageResponse findAll(Pageable pageable, String magType);
    FeatureResponse findById(Long id);
    void loadFeatures();
}
