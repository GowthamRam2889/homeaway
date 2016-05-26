package com.homeaway.qa.solution_1;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class HA_Automation_Exercise_2
{
    public static String path = "http://api.data.gov/nrel/alt-fuel-stations/v1/nearest.json?api_key=X5CfZIkNxBRnxMPwXUwruvq88DOUQHFXgEqm1tuO&location=Austin+TX";
    private static JsonElement address, zip, city, state;
    private static JsonElement id;
    private static ResponseBody responseBody;
    private static String response;

    @BeforeClass (alwaysRun = true)
    public void setUp() {
         responseBody = RestAssured.given().accept("application/json")
                .when().get(path).then().statusCode(200)
                .contentType("application/json").extract().response();

         response = responseBody.asString();
    }

    /**
     * Verifies the station "HYATT AUSTIN" is present in the query of the response
     * @throws Exception
     */
    @Test
    public void verifyHyattAustinInResponse() throws Exception {

        // parsing the response
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response);
        JsonObject object = element.getAsJsonObject();
        JsonArray stations = (JsonArray) object.get("fuel_stations");

        System.out.println("stations:" + stations.toString());

        for(JsonElement object1 : stations) {
            if (object1.toString().contains("HYATT AUSTIN")) {
                address = object1.getAsJsonObject().get("street_address");
                System.out.println("Street address:" + address.toString());
                id = object1.getAsJsonObject().get("id");
            }
        }
        System.out.println("id for HYATT AUSTIN:" + id.toString());

        // Verfiying if "HYATT AUSTIN" is present in the results
        Assert.assertTrue(response.contains("HYATT AUSTIN"));
    }


    /**
     * Validating the Street address and Zip for the Station ID referenced in the
     * previous test
     * @throws Exception
     */
    @Test
    public void getStreetAddress() throws Exception {
        // parsing the response
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response);
        JsonObject object = element.getAsJsonObject();
        JsonArray stations = (JsonArray) object.get("fuel_stations");

        for(JsonElement object1 : stations) {
            if (object1.toString().contains("HYATT AUSTIN")) {
                address = object1.getAsJsonObject().get("street_address");
                zip = object1.getAsJsonObject().get("zip");
                city = object1.getAsJsonObject().get("city");
                state = object1.getAsJsonObject().get("state");
            }
        }
        String streetAddress = address.getAsString().concat(",") + city.getAsString().concat(",") + state.getAsString().concat(",") + zip.getAsString();

        Assert.assertEquals("208 Barton Springs Rd,Austin,TX,78704", streetAddress);
    }


}
