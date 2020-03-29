package com.api;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

/**
 * This class starts the web service
 * The code has been modified slighlty to change BASE_URL
 *
 * @Created 10 Sep 2013
 * @Revised 11 Nov 2019
 */

import java.io.IOException;

public class Cwk2StartUp {
    static final String BASE_URI = "http://localhost:9995/";

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServerFactory.create(BASE_URI);
            server.start();
            System.out.println("Press Enter to stop the server. ");
            System.in.read();
            server.stop(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
