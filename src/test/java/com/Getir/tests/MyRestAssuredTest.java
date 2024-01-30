package com.Getir.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MyRestAssuredTest {
    @BeforeClass
    public void setup() {
        // Set up any preconditions or configurations
        RestAssured.baseURI = "https://catfact.ninja";}

    @Test
    public void testGetCatFact() {
        given()
                .log().all()
                .when()
                .get("/fact")
                .then()
                .log().all()
                .statusCode(200)
                .body("fact", not(emptyOrNullString()))
                .body("length", greaterThan(0));}
}