package com.viking.vikingtask01.Models.Hotel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HotelsInfos {
    List<HotelDetails> Hotel;

    public HotelsInfos(List<HotelDetails> hotel) {
        Hotel = hotel;
    }
}
