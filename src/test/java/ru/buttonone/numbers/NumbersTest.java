package ru.buttonone.numbers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.buttonone.numbers.specifications.NumbersSpecifications;

import java.util.Scanner;

import static io.restassured.RestAssured.given;

public class NumbersTest {

    public static final String NUMBERS_URL = "http://numbersapi.com/";
    public static final String ID_PATH = "/{id}";
    public static final String DATE = "/8/27/date";
    public static final String INCORRECT_URL_WITH_SYMBOLS_INSTEAD_OF_NUMBER = "/8/d/date";
    public static final String URL_WITH_SPACE = "/8//dati";


    @DisplayName("Testing numbersapi with 2")
    @Test
    public void shouldHaveCorrectGet2() {
//        Response response = RestAssured.given().get("http://numbersapi.com/");
//        response.prettyPrint();
//
//        System.out.println("response.statusCode() = " + response.statusCode());
//
//        Assertions.assertEquals(200, response.statusCode());

//        RequestSpecification requestSpecification = new RequestSpecBuilder()
//                .addHeader("Accept-Language", "ru")
//                .setBaseUri(NUMBERS_URL)
//                .build();
//
//        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
//                .log(LogDetail.ALL)
//                .expectStatusCode(200)
//                .build();


        given()//после этого идет блок предусловий
                .spec(NumbersSpecifications.defaultRequestSpecification())//начальные условия
                .when()//когда
                .pathParam("id", 2)
                .get(ID_PATH) //выполняем запрос(метод GET)
                .then()//тогда
                .spec(NumbersSpecifications.defaultResponseSpecification(200));//идут проверки на ответ

        //1. выучить что такое bdd
        //2. создать свои кастомные спецификации в отдельном классе и добавить в
        // каждый тест(возможно туда засунуть константы)
        //3. написать тесты с негативными сценариями


    }

    @DisplayName("Testing numbersapi with math")
    @Test
    public void shouldHaveCorrectGetMath() {
        given()
                .spec(NumbersSpecifications.defaultRequestSpecification())
                .when()
                .pathParam("id", "8")
                .get(ID_PATH)
                .then()
                .contentType(ContentType.TEXT)
                .header("X-Numbers-API-Number", "8")
                .header("X-Numbers-API-Type", "trivia")
                .header("Expires", "0")
                .spec(NumbersSpecifications.defaultResponseSpecification(200));
    }

    @DisplayName("Testing numbersapi with date")
    @Test
    public void shouldHaveCorrectGetDate() {
        given()
                .spec(NumbersSpecifications.defaultRequestSpecification())
                .when()
                .get(DATE)
                .then()
                .contentType(ContentType.TEXT)
                .header("Expires", "0")
                .header("X-Powered-By", "Express")
                .header("X-Numbers-API-Type", "date")
                .spec(NumbersSpecifications.defaultResponseSpecification(200));
    }


    @DisplayName("Testing numbersapi with incorrect url with symbols instead of number")
    @Test
    public void shouldCheckAnotherStatusCode() {
        given()
                .spec(NumbersSpecifications.defaultRequestSpecification())
                .header("Accept-Language", "ru")
                .when()
                .get(INCORRECT_URL_WITH_SYMBOLS_INSTEAD_OF_NUMBER)
                .then()
                .contentType(ContentType.HTML)
                .spec(NumbersSpecifications.defaultResponseSpecification(404));
    }

    @DisplayName("Testing numbersapi with incorrect url with space")
    @Test
    public void shouldCheckIncorrectId() {
        given()
                .spec(NumbersSpecifications.defaultRequestSpecification())
                .when()
                .get(URL_WITH_SPACE)
                .then()
                .contentType(ContentType.HTML)
                .header("X-Powered-By", "Express")
                .header("Content-Encoding", "gzip")
                .spec(NumbersSpecifications.defaultResponseSpecification(404));
    }


}


//given when then (Gherkin)
