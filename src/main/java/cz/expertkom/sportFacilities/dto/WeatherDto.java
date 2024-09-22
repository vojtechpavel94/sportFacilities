package cz.expertkom.sportFacilities.dto;

import lombok.Data;

@Data
/*public class WeatherDto {
    private String date;

    // Getter and setter
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}*/

public class WeatherDto {
    private String startDate;
    private String endDate;

    // Getter and setter for startDate
    public String getStartHour() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    // Getter and setter for endDate
    public String getEndHour() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}