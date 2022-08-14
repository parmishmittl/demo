package com.demo;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class ApiTest {

    public static final String BASE_URL = "https://reqres.in/api";
    public static final String GET_ALL = "/users?page=2";
    public static final String GET_BY_ID = "/users/2";
    public static final String POST_USER = "/users";


    @Test
    public void testGetAllUsers() throws IOException {
        String jsonResponse = given().when().get(BASE_URL+GET_ALL).getBody().prettyPrint();
        System.out.println(jsonResponse);
        String expectedResponse = "{\n" +
                "    \"page\": 2,\n" +
                "    \"per_page\": 6,\n" +
                "    \"total\": 12,\n" +
                "    \"total_pages\": 2,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 7,\n" +
                "            \"email\": \"michael.lawson@reqres.in\",\n" +
                "            \"first_name\": \"Michael\",\n" +
                "            \"last_name\": \"Lawson\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/7-image.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 8,\n" +
                "            \"email\": \"lindsay.ferguson@reqres.in\",\n" +
                "            \"first_name\": \"Lindsay\",\n" +
                "            \"last_name\": \"Ferguson\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/8-image.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 9,\n" +
                "            \"email\": \"tobias.funke@reqres.in\",\n" +
                "            \"first_name\": \"Tobias\",\n" +
                "            \"last_name\": \"Funke\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/9-image.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 10,\n" +
                "            \"email\": \"byron.fields@reqres.in\",\n" +
                "            \"first_name\": \"Byron\",\n" +
                "            \"last_name\": \"Fields\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/10-image.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 11,\n" +
                "            \"email\": \"george.edwards@reqres.in\",\n" +
                "            \"first_name\": \"George\",\n" +
                "            \"last_name\": \"Edwards\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/11-image.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 12,\n" +
                "            \"email\": \"rachel.howell@reqres.in\",\n" +
                "            \"first_name\": \"Rachel\",\n" +
                "            \"last_name\": \"Howell\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/12-image.jpg\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"support\": {\n" +
                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                "    }\n" +
                "}";
        AssertJUnit.assertEquals(jsonResponse, expectedResponse);
    }

    private void delay() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetUserById() {
        given()
                .when().get(BASE_URL+GET_BY_ID)
                .then().statusCode(200);
    }

    @Test
    public void testPostUser() {
/*
        HttpHeaders headers = new HttpHandler();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
*/
        JSONObject data = new JSONObject();

        data.put("email", "john.doe@reqres.in");
        data.put("first_name", "John");
        data.put("last_name", "Doe");
        data.put("avatar", "https://reqres.in/img/faces/12-image.jpg");

        // GIVEN
        given().baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(data.toString())
                // WHEN
                .when().post(POST_USER)
                // THEN
                .then().statusCode(201);
    }

}
