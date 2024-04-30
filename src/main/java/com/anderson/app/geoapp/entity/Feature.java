package com.anderson.app.geoapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Column(name = "external_id", unique = true)
    private String externalId;

    private String place;
    private String time;
    private boolean tsunami;

    @Column(name = "mag_type")
    private String magType;
    private String title;

    private Float magnitude;
    private Float longitude;
    private Float latitude;

    @Column(name = "external_url")
    private String externalUrl;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Feature objeto = (Feature) obj;
        return externalId.equals(objeto.getExternalId());
    }
}
