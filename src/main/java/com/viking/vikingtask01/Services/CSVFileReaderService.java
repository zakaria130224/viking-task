package com.viking.vikingtask01.Services;

import com.viking.vikingtask01.Models.CSVDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CSVFileReaderService {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    public CSVDataModel getDataFromCSVLine(String loc){

        String[] arr= loc.split(",");
        try {

            System.out.println(loc);
            return new CSVDataModel(arr[0],arr[1],arr[2]);
        }catch (Exception e)
        {
            logger.error(e.getMessage());
            return null;
        }

    }

    public List<CSVDataModel> getDataFromCSV(String loc){


        List<CSVDataModel> list=new ArrayList<>();
        String[] line=loc.split("\n");
        for (String ln:line) {
            String[] arr= ln.split(",");
            try {

                System.out.println(loc);
                list.add(new CSVDataModel(arr[0],arr[1],arr[2]));
            }catch (Exception e)
            {
                logger.error(e.getMessage());
            }

        }

        list.remove(0);
        return list;

    }

}
