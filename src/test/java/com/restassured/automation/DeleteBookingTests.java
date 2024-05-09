package com.restassured.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.BaseTestBooking;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTests extends BaseTestBooking {
    @Test
    public void deleteBookingTest() {
        // Create  new booking
        Response response = createBooking();
        // Get Booking Id of new booking
        int id = response.jsonPath().getInt("bookingid");

        Response deletedResponse = RestAssured.given().auth().preemptive().basic("admin", "password123").delete("https://restful-booker.herokuapp.com/booking/" + id);
        deletedResponse.print();

        Assert.assertEquals(deletedResponse.getStatusCode(), 201, "Status code should be 201, but it isn't");

        Response responseGet = RestAssured.given().get("https://restful-booker.herokuapp.com/booking/" + id);
        responseGet.print();

        Assert.assertEquals(responseGet.statusCode(), 404);
    }
}
