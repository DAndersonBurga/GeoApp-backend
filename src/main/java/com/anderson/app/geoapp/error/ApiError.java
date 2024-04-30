package com.anderson.app.geoapp.error;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String message;
    private String method;
    private String path;
    private LocalDateTime timestamp;
}
