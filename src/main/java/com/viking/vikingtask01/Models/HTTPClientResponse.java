package com.viking.vikingtask01.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

@Data
@NoArgsConstructor
public class HTTPClientResponse {
    int code;
    boolean isArray=false;
    JSONObject data;
    JSONArray datalist;

    public HTTPClientResponse(int code, JSONObject data) {
        this.code = code;
        this.data = data;
    }

    public HTTPClientResponse(int code, boolean isArray, JSONArray datalist) {
        this.code = code;
        this.isArray = isArray;
        this.datalist = datalist;
    }
}
