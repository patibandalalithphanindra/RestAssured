package com.restassured.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetBookingIdTests {

    @Test
    public void getAllBookingIdsTest(){
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        response.print();

        Assert.assertEquals(response.statusCode(),200,
                "Status code is expected to be 200, but is not");

        List<Integer> bookingIds = response.jsonPath().getList("bookingId");

        Assert.assertFalse(bookingIds.isEmpty(), "Booking Id List is not expected to be empty");
    }
}
