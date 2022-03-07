package com.viking.vikingtask01.Models.Hotel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HotelDetails {
    String name,latitude,longitude;

    public HotelDetails(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
