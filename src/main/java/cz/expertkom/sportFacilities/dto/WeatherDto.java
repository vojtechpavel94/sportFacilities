package cz.expertkom.sportFacilities.dto;

import lombok.Data;

@Data
public class WeatherDto {
    private String date;

    // Getter and setter
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}