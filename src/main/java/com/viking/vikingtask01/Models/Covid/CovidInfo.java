package com.viking.vikingtask01.Models.Covid;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CovidInfo {
    String confirmed,recovered,critical,deaths,latitude,longitude;

    public CovidInfo(String confirmed, String recovered, String critical, String deaths, String latitude, String longitude) {
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.critical = critical;
        this.deaths = deaths;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
