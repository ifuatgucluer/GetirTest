package com.Getir.pages;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;

public class GetirTest {
    @BeforeClass
    public void setup() {
        // Set up any preconditions or configurations
        RestAssured.baseURI = "https://www.getir.com";}
    @Test
    public void testGetGetir() {
        given()
                .log().all()
                .when()
                .get("/fact")
                .then()
                .log().all()
                .statusCode(200)
                .body("fact", (ResponseAwareMatcher<Response>) not(emptyOrNullString()))
                .body("length", (ResponseAwareMatcher<Response>) greaterThan(0));
    }
    private List<Argument> greaterThan(int i) {
        return null;}    private List<Argument> not(Matcher<String> emptyOrNullString) {
        return null;    }
}
