package com.viking.vikingtask01.Services;

import com.viking.vikingtask01.Models.*;
import com.viking.vikingtask01.Models.Covid.CovidInfo;
import com.viking.vikingtask01.Models.Hotel.HotelDetails;
import com.viking.vikingtask01.Models.Hotel.HotelsInfos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TaskProcessorService {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CovidAPIService covidAPIService;

    public XMLResModel getDataByCountry(CSVDataModel dataModel) throws IOException {
        XMLResModel xmlResModel=new XMLResModel();
        xmlResModel.setCountry(dataModel.getCountry());
        xmlResModel.setDate(dataModel.getDate());

        try {
            List<HotelDetails> hotels=hotelService.getHotelsByCity(dataModel.getCity());
            xmlResModel.setHotelsInCity(new CityHotelModel(dataModel.getCity(),new HotelsInfos(hotels)));

        }catch (Exception e){
            logger.error("Failed on gathering hotel info: {}",e.getMessage());
        }

        SimpleDateFormat yy = new SimpleDateFormat( "dd.MM.yy" );
        SimpleDateFormat yyyy = new SimpleDateFormat( "yyyy-MM-dd" );
        Date actualDate = null;
        try {
            actualDate = yy.parse( dataModel.getDate() );
        }
        catch ( ParseException pe ) {
            logger.error(pe.getMessage());
        }

        try {
            CovidInfo covid=covidAPIService.getCovidByCountry(dataModel.getCountry(),yyyy.format( actualDate ));
            xmlResModel.setCovidInfo(covid);
            //xmlResModel.setCovidInfo(new CovidInfo("4822","6455","545","566","100","51"));

        }catch (Exception e){
            logger.error("Failed on gathering covid info: {}",e.getMessage());
        }

        return xmlResModel;
    }
}
