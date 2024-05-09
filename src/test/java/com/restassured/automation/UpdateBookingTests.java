package com.restassured.automation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.BaseTestBooking;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateBookingTests extends BaseTestBooking {
    @Test
    public void updateBookingTest() {
        // Create new booking
        Response response = createBooking();
        // Get Booking Id of new booking
        int id = response.jsonPath().getInt("bookingid");

        // Create JSON Body
        JSONObject body = new JSONObject();
        body.put("firstname", "hanumath");
        body.put("lastname", "prasad");
        body.put("totalprice", 150);
        body.put("depositpaid", true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2024-05-09");
        bookingdates.put("checkout", "2024-05-10");
        body.put("bookingdates", bookingdates);

        body.put("additionalneeds", "pen");

        Response responseUpdate = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin","password123")
                .contentType(ContentType.JSON)
                .body(body.toString())
                .put("https://restful-booker.herokuapp.com/booking/" + id);

        Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Response status should be 200");

        responseUpdate.print();

        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = responseUpdate.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName, "hanumath");

        String actualLastName = responseUpdate.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, "prasad");

        String actualCheckIn = responseUpdate.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckIn,"2024-05-09");

        softAssert.assertAll();
    }


}