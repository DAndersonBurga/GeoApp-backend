package com.anderson.app.geoapp.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
public class CreateCommentRequest {

    @JsonProperty("feature_id")
    @NotNull
    @Min(1)
    private Long featureId;

    @NotBlank
    @Length(min = 1, max = 150)
    private String body;
}
