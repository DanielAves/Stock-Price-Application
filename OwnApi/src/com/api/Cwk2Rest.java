package com.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;
import java.text.DecimalFormat;

/**
 * Cwk2Rest handles Post and Get requests aimed at localhost:9995
 *
 * @author Dan Aves
 */
@Path("/api")
public class Cwk2Rest {

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private static Double currentPrice;
    private static Double pastPrice;

    public static Double getCurrentPrice() {
        return currentPrice;
    }

    public static void setCurrentPrice(Double currentPrice) {
        Cwk2Rest.currentPrice = currentPrice;
    }

    public static Double getPastPrice() {
        return pastPrice;
    }

    public static void setPastPrice(Double pastPrice) {
        Cwk2Rest.pastPrice = pastPrice;
    }


    @GET
    @Path("/code/get/{current}/{past}")
    @Produces(MediaType.TEXT_PLAIN)
    public String percentageDiff(@PathParam("current") Boolean a, @PathParam("past") Boolean b) throws Exception{

        String result = "";

        if(a && b == true){
            double diff = Cwk2Rest.getCurrentPrice() - Cwk2Rest.getPastPrice();

            double percentagediff = diff/Cwk2Rest.getPastPrice();
            percentagediff = percentagediff * 100;
            result = df2.format(percentagediff) + "%";
        }
        return result;
        }

    @POST
    @Path("/code/post/{a}/{b}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String StoreStockPrices(@PathParam("a") double a, @PathParam("b") double b) throws URISyntaxException {

        Cwk2Rest.setCurrentPrice(a);
        Cwk2Rest.setPastPrice(b);

        return "set successfully";
    }
}
