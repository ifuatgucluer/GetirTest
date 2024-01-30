package org.example.getir.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Getir {
        private static final String BASE_URL = "https://catfact.ninja";

        public static Response getCatFact() {
            return RestAssured.get(BASE_URL + "/fact");
        }

        // Add more methods for additional endpoints if needed
    }


