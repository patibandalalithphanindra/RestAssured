package com.restassured.automation;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class HealthCheckTests {

    @Test
    public void healthCheckTest() {
        given().when().get("https://restful-booker.herokuapp.com/ping\n").then().assertThat().statusCode(201);
    }
}
