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
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLResModel.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(res, sw);

            String xmlContent = sw.toString();
            logger.info( xmlContent );
            return xmlContent;
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }
}
