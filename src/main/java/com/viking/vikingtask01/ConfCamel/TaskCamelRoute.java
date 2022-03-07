package com.viking.vikingtask01.ConfCamel;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TaskCamelRoute {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Value("${ftp.user}")
    private String ftpUser;

    @Value("${ftp.host}")
    private String ftpHost;

    @Value("${ftp.port}")
    private String ftpPort;

    @Value("${ftp.out.path}")
    private String ftpPath;

    @Value("${ftp.password}")
    private String ftpPassword;

    @Value("${ftp.filename}")
    private String ftpFileName;


    @Autowired
    CamelProcessor camelProcessor;

    @Async
    public RouteBuilder createRouteBuilder() {

        RouteBuilder builder = new RouteBuilder() {
            public void configure() {
                //errorHandler(deadLetterChannel("mock:error"));
                //from("file:D:\\WorkStation\\viking-task01\\source?fileName=data.csv&noop=true&idempotent=true")
                from(String.format("ftp://%s@%s:%s/%s/?password=%s&fileName=%s&passiveMode=true",ftpUser,ftpHost,ftpPort,ftpPath,ftpPassword,ftpFileName))
                        //from("ftp://userX@localhost:21/data/?password=zaka1324&fileName=data.csv&passiveMode=true")
                        .split(body().tokenize("\n",1,true))
                        .process(camelProcessor)
                        //.marshal(populateStreamDef())
                        .to("log:?level=INFO&showBody=true")
                        .recipientList(header("outpath"));
            }
        };
        return builder;

    }
}
