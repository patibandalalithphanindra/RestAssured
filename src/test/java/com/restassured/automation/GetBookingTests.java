package com.restassured.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTests {
    @Test
    public void getBookingTest(){
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/6");
        response.print();

        Assert.assertEquals(response.statusCode(),200,"Response status should be 200, but it isn't");

        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = response.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName, "Mary");

        String actualLastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, "Jones");

        String actualCheckIn = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckIn,"2017-11-06");

        softAssert.assertAll();
    }
}
