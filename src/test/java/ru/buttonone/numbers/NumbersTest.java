package ru.buttonone.numbers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumbersTest {

    public static final String NUMBERS_URL = "http://numbersapi.com";

    @DisplayName("Testing numbersapi with 2")
    @Test
    public void shouldHaveCorrectGet2() {
//        Response response = RestAssured.given().get("http://numbersapi.com/2");
//        response.prettyPrint();
//
//        System.out.println("response.statusCode() = " + response.statusCode());
//
//        Assertions.assertEquals(200, response.statusCode());

        RestAssured
                .given()
                .header(new Header("language", "fr"))
                .baseUri(NUMBERS_URL)
                .when()
                .get("/2")
                .then()
                .contentType(ContentType.TEXT)
                .log().all()
                .statusCode(200);

    }

    @DisplayName("Testing numbersapi with math")
    @Test
    public void shouldHaveCorrectGetMath(){
        RestAssured
                .given()
                .header(new Header("language", "fr"))
                .baseUri(NUMBERS_URL)
                .when()
                .get("/8/math")
                .then()
                .contentType(ContentType.TEXT)
                .log().all()
                .header("X-Numbers-API-Number", "8")
                .header("X-Numbers-API-Type", "math")
                .header("Expires", "0")
                .statusCode(200);
    }

    @DisplayName("Testing numbersapi with date")
    @Test
    public void shouldHaveCorrectGetDate(){
        RestAssured
                .given()
                .baseUri(NUMBERS_URL)
                .when()
                .get("/8/27/date")
                .then()
                .contentType(ContentType.TEXT)
                .log().all()
                .header("Expires", "0")
                .header("X-Powered-By", "Express")
                .header("X-Numbers-API-Type","date")
                .statusCode(200);
    }


}


//given when then (Gherkin)
