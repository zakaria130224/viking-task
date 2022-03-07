package com.viking.vikingtask01.Services;

import com.viking.vikingtask01.Models.XMLResModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Service
public class XMLWriterService {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    public String jaxbObjectToXML(XMLResModel res)
    {
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLResModel.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            jaxbMarshaller.marshal(res, sw);

            //Verify XML Content
            String xmlContent = sw.toString();
            System.out.println( xmlContent );
            return xmlContent;
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }
}
