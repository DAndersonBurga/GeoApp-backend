package com.anderson.app.geoapp.repository;

import com.anderson.app.geoapp.entity.Feature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    Page<Feature> findAllByMagType(String magType, Pageable pageable);
}
