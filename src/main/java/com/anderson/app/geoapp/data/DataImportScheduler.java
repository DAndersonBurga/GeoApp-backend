package com.anderson.app.geoapp.data;

import com.anderson.app.geoapp.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataImportScheduler {

    private final FeatureService featureService;

    @Scheduled(fixedRate = 86400000)
    public void importData() {
        featureService.loadFeatures();
    }
}
