package com.anderson.app.geoapp.entity.dto.feature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeatureCollectionDto {
    private List<FeatureDto> features;
}
