package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class BaseTestBooking {
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
                .given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("https://restful-booker.herokuapp.com/booking");

        return response;
    }
}
