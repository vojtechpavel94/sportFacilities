package cz.expertkom.sportFacilities.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {
    private final String API_KEY = "4ccef0bbdc4fb918a5344bbbd8d4932a";
    private final RestTemplate restTemplate = new RestTemplate();

    public String getWeather(double latitude, double longitude) {
        log.info("#WS&gw01: getWeather called, latitude= {}, longitude= {}", latitude, longitude);
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s&units=metric&lang=cz",
                latitude, longitude, API_KEY);
        return restTemplate.getForObject(url, String.class);
    }
}
