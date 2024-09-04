package cz.expertkom.sportFacilities.service;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {
    private final String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();
    public WeatherService() {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("WEATHER_API_KEY");
    }
    // Use the apiKey in your methods
    public String getWeather ( double latitude, double longitude){
        log.info("#WS&gw01: getWeather called, latitude= {}, longitude= {}", latitude, longitude);
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s&units=metric&lang=cz",
                latitude, longitude, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
}
