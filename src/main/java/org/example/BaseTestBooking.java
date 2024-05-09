package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BaseTestBooking {
    protected RequestSpecification specification;

    @BeforeMethod
    public void setUp() {
        specification = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }

    protected Response createBooking() {
        JSONObject body = new JSONObject();
        body.put("firstname", "lalith phanindra");
        body.put("lastname", "patibanda");
        body.put("totalprice", 150);
        body.put("depositpaid",true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2024-05-08");
        bookingdates.put("checkout", "2024-05-10");
        body.put("bookingdates", bookingdates);

        body.put("additionalneeds", "pen");

        Response response = RestAssured
                .given(specification)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("/booking");

        return response;
    }
}
