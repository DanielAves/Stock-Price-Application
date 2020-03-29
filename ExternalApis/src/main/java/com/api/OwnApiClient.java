package com.api;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * This OwnApiClient class calls my own local Api hosted at 'localhost:9995'
 * Post and Get requests are used to Post and Get information from the Api respectively
 * Past and Present share prices are gathered from companyinfo.getCurrentPrice & companyinfo.getPastPrice
 * @author Dan Aves
 */

public class OwnApiClient {

    static final String REST_URI = "http://localhost:9995/";
    static final String GET_PATH = "api/code/get";
    static final String POST_PATH = "api/code/post";

    public void Client() {

        long startTime = System.currentTimeMillis();

        CompanyInfo companyinfo = new CompanyInfo();

        try {

            ClientConfig config = new DefaultClientConfig();
            Client client = Client.create(config);
            WebResource service = client.resource(REST_URI);

            WebResource setPrice = service.path(POST_PATH).path(companyinfo.getCurrentPrice() + "/" + companyinfo.getPastPrice());
            if (postOutputAsText(setPrice).equals("set successfully")) {
                WebResource percentageService = service.path(GET_PATH).path(true + "/" + true);
                companyinfo.setPercenageChange(getOutputAsText(percentageService));
                System.out.println("Returned percentage difference from Own API:");
                System.out.println(getOutputAsText(percentageService));
            }
        }
        catch (Exception e) {
                companyinfo.setPercenageChange("Nothing returned here, please make sure 'Cwk2StartUp' is running");
        }

        System.out.println("\nOwn Apirun time:");
        System.out.println(System.currentTimeMillis()-startTime + "milliseconds \n");

    }


    private static String getOutputAsText(WebResource service) {
        return service.accept(MediaType.TEXT_PLAIN).get(String.class);
    }
    private static String postOutputAsText(WebResource service) {
        return service.accept(MediaType.TEXT_PLAIN).post(String.class);
    }
}
