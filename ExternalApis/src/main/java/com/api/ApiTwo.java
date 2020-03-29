package com.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

/**
 * This ApiTwo class calls the Api hosted at 'alphavantage.com' using a stock code returned from the previous webservice
 * The stock code from the previous service is obtained from companyinfo.getStockcode()
 * JSON is parsed to fetch the last days trading close price and a past price from 50 days ago
 *
 * @author Dan Aves
 */

public class ApiTwo {

    String AlphaVantageKey = "M5MJGQ11KLUVG11B";

    public void invokeAlphaVantage(){

        URL url;
        HttpURLConnection connection = null;
        InputStream is = null;
        CompanyInfo companyinfo = new CompanyInfo();
        String symbol = companyinfo.getStockcode();

        long startTime = System.currentTimeMillis();

        try
        {
            String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED"
                    + "&symbol=" + symbol
                    + "&apikey=" + AlphaVantageKey ;

            url = new URL (urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            is = connection.getInputStream();
            BufferedReader theReader =
                    new BufferedReader(new InputStreamReader(is, "UTF-8"));

            System.out.println("API two run time:");
            System.out.println(System.currentTimeMillis()-startTime + "milliseconds \n");

            StringBuilder resultStringBuilder = new StringBuilder();
            String reply;
            while ((reply = theReader.readLine()) != null)
            {
                resultStringBuilder.append(reply);
            }
            System.out.println("API two returned JSON:");
            System.out.println(resultStringBuilder.toString());
            System.out.println("\n");

            JSONObject obj = new JSONObject(resultStringBuilder.toString());
            JSONObject StockPrice = obj.getJSONObject("Time Series (Daily)");
            //50 days in past
            LocalDate datepast = LocalDate.now().minusDays(50);
            //Set to previous day
            LocalDate currentDate = LocalDate.now().minusDays(1);

            DayOfWeek dayCurrent = DayOfWeek.of(currentDate.get(ChronoField.DAY_OF_WEEK));
            DayOfWeek dayPast = DayOfWeek.of(datepast.get(ChronoField.DAY_OF_WEEK));
            //Handle weekends when stock markets are closed (so no data)
            switch (dayCurrent){
                case SATURDAY:
                    currentDate = currentDate.minusDays(1);
                    break;
                case SUNDAY:
                    currentDate = currentDate.minusDays(2);
                    break;
            }
            switch (dayPast){
                case SATURDAY:
                    datepast = datepast.minusDays(1);
                    break;
                case SUNDAY:
                    datepast = datepast.minusDays(2);
                    break;
                case MONDAY:
                    datepast = datepast.minusDays(3);
                    break;

            }

            JSONArray key = StockPrice.names();
            for (int i=0; i < key.length(); i++){
                String date = (key.getString(i));
                String prices = StockPrice.get(date).toString();
                String [] splitPrices = prices.split(",");
                //Split further at '5. Closing price , after :'
                String [] closingPrice = splitPrices[5].split(":");
                //Remove quotes
                closingPrice[1] = closingPrice[1].replace("\"", "");

                if(date.compareTo(currentDate.toString()) == 0){
                    companyinfo.setCurrentDate(date);
                    companyinfo.setCurrentTime("1 Day ago - Last Closing");
                    companyinfo.setCurrentPrice(Double.parseDouble(closingPrice[1]));
                }
                if(date.compareTo(datepast.toString()) == 0){
                    companyinfo.setPastDate(date);
                    companyinfo.setPastTime("50 Days ago");
                    companyinfo.setPastPrice(Double.parseDouble(closingPrice[1]));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            companyinfo.setInvalidCompany(true);
        }
    }
}
