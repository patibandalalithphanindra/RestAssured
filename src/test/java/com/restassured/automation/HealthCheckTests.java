package com.restassured.automation;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.example.BaseTestBooking;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class HealthCheckTests extends BaseTestBooking {

    @Test
    public void healthCheckTest() {
        given(specification).when().get("/ping\n").then().assertThat().statusCode(201);
    }

    @Test
    public void headersAndCookiesCheckTest() {
        Header someHeader = new Header("some_name", "some_value");
        specification.header(someHeader);
        Cookie someCookie = new Cookie.Builder("some_cookie_name", "some_cookie_value").build();
        specification.cookie(someCookie);
        Response response = RestAssured.given(specification).header(someHeader).cookie(someCookie).get("/ping");

        // Get Headers
        Headers headers = response.getHeaders();
        Header serverHeader1 = headers.get("Server");
        String serverHeader2 = response.getHeader("Server");

        // Get Cookies
        Cookies cookies = response.getDetailedCookies();
        System.out.println(headers);
        System.out.println(cookies);
        System.out.println(response);
    }
}
