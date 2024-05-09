package com.restassured.automation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.BaseTestBooking;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PatchBookingTests extends BaseTestBooking {
    @Test
    public void patchBookingTest() {
        // Create new booking
        Response response = createBooking();
        // Get Booking Id of new booking
        int id = response.jsonPath().getInt("bookingid");

        // Create JSON Body
        JSONObject body = new JSONObject();
        body.put("firstname", "raju");
        body.put("lastname", "singh");

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2024-06-09");
        bookingdates.put("checkout", "2024-07-10");
        body.put("bookingdates", bookingdates);

        Response responseUpdate = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin","password123")
                .contentType(ContentType.JSON)
                .body(body.toString())
                .patch("https://restful-booker.herokuapp.com/booking/" + id);

        Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Response status should be 200");

        responseUpdate.print();

        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = responseUpdate.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName, "raju");

        String actualLastName = responseUpdate.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, "singh");

        String actualCheckIn = responseUpdate.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckIn,"2024-06-09");

        String actualCheckOut = responseUpdate.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(actualCheckOut,"2024-07-10");

        softAssert.assertAll();
    }


}