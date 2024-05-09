package com.restassured.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.BaseTestBooking;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetBookingIdTests extends BaseTestBooking {

    @Test
    public void getAllBookingIdsTest() {
        Response response = RestAssured.given(specification).get("/booking");
        response.print();

        Assert.assertEquals(response.statusCode(),200,
                "Status code is expected to be 200, but is not");

        List<Integer> bookingIds = response.jsonPath().getList("bookingId");

        Assert.assertFalse(bookingIds.isEmpty(), "Booking Id List is not expected to be empty");
    }

    @Test
    public void getBookingIdsWithFilter() {
        specification.queryParam("firstname", "Eric");
        Response response = RestAssured.given(specification).get("/booking");
        response.print();

        Assert.assertEquals(response.statusCode(),200,
                "Status code is expected to be 200, but is not");

        List<Integer> bookingIds = response.jsonPath().getList("bookingId");

        Assert.assertFalse(bookingIds.isEmpty(), "Booking Id List is not expected to be empty");
    }
}
