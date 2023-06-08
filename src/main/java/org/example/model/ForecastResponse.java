package org.example.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForecastResponse {

    // Class representing API response containing weather forecasts

    private List<Forecast> list;

    @Getter
    @Setter
    public static class Forecast {
        private String dt_txt;
        private Main main;

        public Double getTemperature() {
            return main.getTemp();
        }

        @Getter
        @Setter
        public static class Main {
            private Double temp;
        }
    }
}
