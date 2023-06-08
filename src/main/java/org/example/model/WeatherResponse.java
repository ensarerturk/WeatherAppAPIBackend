package org.example.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {

    // Represents the data contained in the weather API response.

    private String name;
    private Main main;

    @Getter
    @Setter
    public static class Main {
        private Double temp;
    }
}

