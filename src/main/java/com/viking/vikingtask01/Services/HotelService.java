package com.viking.vikingtask01.Services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viking.vikingtask01.Models.HTTPClientResponse;
import com.viking.vikingtask01.Models.Hotel.HotelDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Value("${hotel.api.baseurl}")
    private String baseUrl;

    @Value("${hotel.api.apihost}")
    private String apihost;

    @Value("${hotel.api.apikey}")
    private String apikey;

    @Autowired
    private WebClientService webClientService;

    public List<HotelDetails> getHotelsByCity(String city) throws IOException {
        List<HotelDetails> list=new ArrayList<HotelDetails>();
       try {
           HTTPClientResponse hotel=webClientService.get(baseUrl.replace("#CITY",city),apihost,apikey);
           ObjectMapper om= new ObjectMapper();
           om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

           hotel.getData().getJSONArray("suggestions").toList().stream().map(x -> (HashMap) x).forEach(map -> {
               if (map.get("group").equals("HOTEL_GROUP") )
               {
                   Object hotels = map.get("entities");
                   Arrays.stream(((ArrayList) hotels).toArray()).limit(3).collect(Collectors.toList()).forEach(x->{
                       HotelDetails hotelDetails=om.convertValue(x,HotelDetails.class);
                       list.add(hotelDetails);
                   });
               }
           });
           System.out.println(list);
           return list;
       }catch (Exception ex){
           logger.error(ex.getMessage());
           return list;
       }

    }
}
