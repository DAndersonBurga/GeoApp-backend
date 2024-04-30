package com.anderson.app.geoapp.entity.dto.feature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PropertiesDto {
    private Float mag;
    private String place;
    private String time;
    private String url;
    private int tsunami;
    private String magType;
    private String title;
}
