package com.viking.vikingtask01.Models;

import com.viking.vikingtask01.Models.Covid.CovidInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement
public class XMLResModel {

    String Country;
    String Date;

    CityHotelModel HotelsInCity;

    CovidInfo CovidInfo;

    public XMLResModel(String country) {
        Country = country;
    }
}
