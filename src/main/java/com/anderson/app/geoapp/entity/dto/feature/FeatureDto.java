package com.anderson.app.geoapp.entity.dto.feature;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class FeatureDto {
    private String id;

    @JsonProperty("properties")
    private PropertiesDto properties;

    @JsonProperty("geometry")
    private GeometryDto geometry;
}
