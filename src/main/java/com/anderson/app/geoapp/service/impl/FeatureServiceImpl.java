package com.anderson.app.geoapp.service.impl;

import com.anderson.app.geoapp.controllers.response.FeaturePageResponse;
import com.anderson.app.geoapp.controllers.response.FeatureResponse;
import com.anderson.app.geoapp.entity.Feature;
import com.anderson.app.geoapp.entity.dto.feature.FeatureCollectionDto;
import com.anderson.app.geoapp.entity.dto.feature.FeatureDto;
import com.anderson.app.geoapp.exceptions.FeatureNotFoundException;
import com.anderson.app.geoapp.repository.FeatureRepository;
import com.anderson.app.geoapp.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    @Override
    public FeaturePageResponse findAll(Pageable pageable, String magType) {
        Page<Feature> featurePage;
        if(magType != null && !magType.isBlank()) {
            featurePage = featureRepository.findAllByMagType(magType, pageable);
        } else {
            featurePage = featureRepository.findAll(pageable);
        }
        List<Feature> featureList = featurePage.getContent();

        FeaturePageResponse featurePageResponse = new FeaturePageResponse();

        featurePageResponse.setPagination(Map.of(
            "current_page", featurePage.getPageable().getPageNumber(),
            "total", featurePage.getTotalElements(),
            "per_page", featurePage.getPageable().getPageSize()
        ));

        List<FeatureResponse> featureResponseList = featureList.stream()
                .map(feature -> createFeatureResponse(feature))
                .toList();

        featurePageResponse.setData(featureResponseList);

        return featurePageResponse;
    }

    @Override
    public FeatureResponse findById(Long id) {
        Feature feature = featureRepository.findById(id)
                .orElseThrow(() -> new FeatureNotFoundException("Feature not found"));

        return createFeatureResponse(feature);
    }

    @Override
    public void loadFeatures() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";
        ResponseEntity<FeatureCollectionDto> response = restTemplate.getForEntity(url, FeatureCollectionDto.class);

        Set<Feature> features = new HashSet<>();

        if(response.getStatusCode() == HttpStatus.OK) {
            FeatureCollectionDto featureCollectionDto = response.getBody();
            featureCollectionDto.getFeatures().forEach(featureDto -> {

                if(!validateRegisters(featureDto)) {
                    return;
                }

                Feature feature = Feature.builder()
                        .magnitude(featureDto.getProperties().getMag())
                        .place(featureDto.getProperties().getPlace())
                        .time(featureDto.getProperties().getTime())
                        .externalUrl(featureDto.getProperties().getUrl())
                        .tsunami(featureDto.getProperties().getTsunami() == 1)
                        .type("feature")
                        .magType(featureDto.getProperties().getMagType())
                        .title(featureDto.getProperties().getTitle())
                        .longitude(featureDto.getGeometry().getCoordinates()[0])
                        .latitude(featureDto.getGeometry().getCoordinates()[1])
                        .externalId(featureDto.getId())
                        .build();

                features.add(feature);
            });


        }

        try {
            featureRepository.saveAll(features);
        } catch (Exception ignored) {}
    }

    private boolean validateRegisters(FeatureDto featureDto) {
        if(featureDto.getGeometry().getCoordinates() == null) return false;

        Float longitude = featureDto.getGeometry().getCoordinates()[0];
        Float latitude = featureDto.getGeometry().getCoordinates()[1];
        Float magnitude = featureDto.getProperties().getMag();

        boolean isLongitudeValid = longitude != null && (longitude >= -180.0 && longitude <= 180.0);
        boolean isLatitudeValid = latitude != null && (latitude >= -90.0 && latitude <= 90.0);
        boolean isMagnitudeValid = magnitude != null && (magnitude >= -1.0 && magnitude <= 10.0);

        return  featureDto.getProperties().getPlace() != null &&
                featureDto.getProperties().getUrl() != null &&
                featureDto.getProperties().getMagType() != null &&
                featureDto.getProperties().getTitle() != null &&
                isLatitudeValid && isLongitudeValid && isMagnitudeValid;
    }

    private FeatureResponse createFeatureResponse(Feature feature) {
        return FeatureResponse.builder()
                .id(feature.getId())
                .type(feature.getType())
                .attributes(Map.of(
                        "external_id", feature.getExternalId(),
                        "magnitude", feature.getMagnitude(),
                        "place", feature.getPlace(),
                        "time", feature.getTime(),
                        "tsunami", feature.isTsunami(),
                        "mag_type", feature.getMagType(),
                        "title", feature.getTitle(),
                        "longitude", feature.getLongitude(),
                        "latitude", feature.getLatitude()
                ))
                .links(Map.of(
                        "external_url", feature.getExternalUrl()
                ))
                .build();
    }

}
