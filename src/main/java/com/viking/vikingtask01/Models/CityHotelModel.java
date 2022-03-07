package com.viking.vikingtask01.Models;

import com.viking.vikingtask01.Models.Hotel.HotelsInfos;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityHotelModel {
    String CityName;
    HotelsInfos Hotels;

    public CityHotelModel(String cityName, HotelsInfos hotels) {
        CityName = cityName;
        Hotels = hotels;
    }
}
