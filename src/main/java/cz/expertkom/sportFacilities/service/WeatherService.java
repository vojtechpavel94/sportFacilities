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
    //Dotenv - využití dependency pro schování klíče v .env file, měl jsem tam security problém při pushi do gitu.
    public WeatherService() {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("WEATHER_API_KEY");
    }
    //TODO - přidat datum
    public String getWeather(double latitude, double longitude, String dt) {
        log.info("#WS&gw01: getWeather called, latitude= {}, longitude= {}, date= {}", latitude, longitude, dt);

        String url = String.format(
                "http://api.weatherapi.com/v1/history.json?key=%s&q=%f,%f&dt=%s",
                apiKey, latitude, longitude, dt);

        return restTemplate.getForObject(url, String.class);
    }
}
