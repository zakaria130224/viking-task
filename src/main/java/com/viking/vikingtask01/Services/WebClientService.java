package com.viking.vikingtask01.Services;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.viking.vikingtask01.Models.HTTPClientResponse;
import org.apache.commons.httpclient.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WebClientService {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    public HTTPClientResponse get(String Url, String host, String apikey) throws IOException {
        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(Url)
                    .get()
                    .addHeader("x-rapidapi-host", host)
                    .addHeader("x-rapidapi-key", apikey)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code()== HttpStatus.SC_OK){
                String resbody=response.body().string();
                Object json = new JSONTokener(resbody).nextValue();
                if (json instanceof JSONObject)
                {
                    return new HTTPClientResponse(HttpStatus.SC_OK,new JSONObject(resbody));
                }
                else if (json instanceof JSONArray)
                {

                    return new HTTPClientResponse(HttpStatus.SC_OK,true,new JSONArray(resbody));
                }else {
                    JSONObject item = new JSONObject();
                    item.put("data", resbody);
                    return new HTTPClientResponse(HttpStatus.SC_OK,item);

                }

            }
            else {
                logger.error("API Response: {}", response.body().string());
                return new HTTPClientResponse(response.code(),new JSONObject(response.body().string()));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            JSONObject item = new JSONObject();
            item.put("message", "Something wet wrong!");
            return new HTTPClientResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR,item);

        }

    }


}
