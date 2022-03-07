package com.viking.vikingtask01.ConfCamel;

import com.viking.vikingtask01.Models.CSVDataModel;
import com.viking.vikingtask01.Models.XMLResModel;
import com.viking.vikingtask01.Services.CSVFileReaderService;
import com.viking.vikingtask01.Services.TaskProcessorService;
import com.viking.vikingtask01.Services.XMLWriterService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;


@Service
public class CamelProcessor implements Processor {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Value("${ftp.user}")
    private String ftpUser;

    @Value("${ftp.host}")
    private String ftpHost;

    @Value("${ftp.port}")
    private String ftpPort;

    @Value("${ftp.path}")
    private String ftpPath;

    @Value("${ftp.password}")
    private String ftpPassword;

    @Value("${ftp.filename}")
    private String ftpFileName;


    @Autowired
    private CSVFileReaderService cvsFileReaderSrvice;

    @Autowired
    private  TaskProcessorService taskProcessorService;

    @Autowired
    private XMLWriterService xmlWriterService;

    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            String body = exchange.getIn().getBody(String.class);

            CSVDataModel res=cvsFileReaderSrvice.getDataFromCSVLine(body);

            XMLResModel xmlResModel=taskProcessorService.getDataByCountry(res);
            //List to XML
            String str=xmlWriterService.jaxbObjectToXML(xmlResModel);
            // change the message to say Hello
            exchange.getOut().setBody(str);
            // copy headers from IN to OUT to propagate them
            exchange.getOut().setHeaders(exchange.getIn().getHeaders());

            //String destDir = String.format("file:D:\\WorkStation\\viking-task01\\destination?fileName=%s.xml", res.getCountry());
            String destDir = String.format("ftp://%s@%s:%s/%s/?password=%s&fileName=%s.xml&passiveMode=true",ftpUser,ftpHost,ftpPort,ftpPath,ftpPassword,xmlResModel.getCountry());
            exchange.getIn().setHeader("outpath",destDir);
        }catch ( Exception e ) {
            logger.error(e.getMessage());
        }


    }
}
