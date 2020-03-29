package com.api;

import java.util.HashMap;
import java.lang.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This ApiOne class calls the Api hosted at 'Tradier.com' using a company name entered by a user on localhost:8090
 * JSON parsed to fetch the returned 'Symbol'
 *
 * @author Dan Aves
 */


public class ApiOne {

    //HashMap<String, String> stockCodes = new HashMap<String, String>();

    public void stockCode() throws Exception{

        //Invoke object for access to parameter 'Company Name'
        CompanyInfo companyinfo = new CompanyInfo();

        long startTime = System.currentTimeMillis();

        companyinfo.setInvalidCompany(false);

        final HttpUriRequest request = RequestBuilder
                .get("https://sandbox.tradier.com/v1/markets/search")
                .addHeader("Authorization", "Bearer A56MN5GG4wNUetvbzgYVmLyiaSoU")
                .addHeader("Accept", "application/json")
                .addParameter("q", companyinfo.getCompanyName())
                .addParameter("indexes", "false")
                .build();

        final HttpResponse response = HttpClientBuilder.create().build().execute(request);

        final String jsonString = EntityUtils.toString(response.getEntity());
        final JsonNode json = new ObjectMapper().readTree(jsonString);

        System.out.println("API one run time:");
        System.out.println(System.currentTimeMillis()-startTime + "milliseconds \n");

        //Print JSON result
        System.out.println("API One returned JSON:");
        System.out.println(jsonString + "\n");
        String Symbol = "";


        if (!jsonString.contains("null")){
            JSONObject jo = new JSONObject(jsonString);
            JSONObject Stock = jo.getJSONObject("securities");

            //Handle responses (object, array)
            if (Stock.get("security") instanceof JSONArray)
            {
                JSONArray arr = Stock.getJSONArray("security");
                for (int i = 0; i < arr.length(); i++){
                    Symbol = arr.getJSONObject(0).getString("symbol");
                }
            }
            //Handles differing responses (array, array)
            else{
                JSONObject security = Stock.getJSONObject("security");
                Symbol = security.get("symbol").toString();
            }
        }
        else{
            companyinfo.setInvalidCompany(true);
        }
        companyinfo.setStockcode(Symbol);
    }
}
