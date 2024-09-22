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

    /*public String getWeather(double latitude, double longitude, String dt, String dt2) {
        log.info("#WS&gw01: getWeather called, latitude= {}, longitude= {}, date= {}", latitude, longitude, dt);*/

        /*String url = String.format(
                "http://api.weatherapi.com/v1/history.json?key=%s&q=%f,%f&dt=%s",
                apiKey, latitude, longitude, dt);

            return restTemplate.getForObject(url, String.class);
        }
    }*/

   public String getWeather(float latitude, float longitude, String startHour, String endHour) {
       log.info("#WS&gw01: getWeather called, latitude= {}, longitude= {}, startHour= {}, endHour= {}", latitude, longitude, startHour, endHour);

       String url = String.format(
               "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&start_hour=%s&end_hour=%s&hourly=temperature_2m",
               latitude, longitude, startHour, endHour
       );

       log.info("URL: {}", url);
       String response = restTemplate.getForObject(url, String.class);
       log.info("API Response: {}", response);

       return response;
   }
}