package com.restassured.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.BaseTestBooking;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTests extends BaseTestBooking {
    @Test
    public void getBookingTest(){
        Response createdResponse = createBooking();
        createdResponse.print();

        int bookingid = createdResponse.jsonPath().getInt("bookingid");
        specification.pathParam("bookingid", bookingid);
        Response response = RestAssured.given(specification).get("/booking/{bookingid}");
        response.print();

        Assert.assertEquals(response.statusCode(),200,"Response status should be 200, but it isn't");

        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = response.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName, "lalith phanindra");

        String actualLastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, "patibanda");

        String actualCheckIn = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckIn,"2024-05-08");

        softAssert.assertAll();
    }
}
